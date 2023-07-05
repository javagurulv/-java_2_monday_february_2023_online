package shop.acceptance_tests.manager;

import org.junit.Ignore;
import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.jdbc.Sql;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.transaction.annotation.Transactional;
import shop.core.database.ItemRepository;
import shop.core.domain.item.Item;
import shop.core.services.actions.manager.AddItemToShopServiceImpl;
import shop.core_api.dto.item.ItemDTO;
import shop.core_api.dto.item.Money;
import shop.core_api.requests.manager.AddItemToShopRequest;
import shop.core_api.responses.manager.AddItemToShopResponse;

import java.math.BigDecimal;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

@RunWith(SpringRunner.class)
@SpringBootTest
@Transactional
public class AddItemsToTheShopAcceptanceTest {

    @Autowired
    private ItemRepository itemRepository;
    @Autowired
    private AddItemToShopServiceImpl addItemToShopService;

    @Sql({"/testDatabaseTableCreation.sql", "/testDatabaseDataInsertion.sql"})
    @Test
    @Ignore
    void shouldAddItemsToTheShop() {
        int shopItemCountBefore = itemRepository.findAll().size();
        AddItemToShopResponse addItemToShopResponse =
                addItemToShopService.execute(new AddItemToShopRequest(new ItemDTO(null, "new item 1", Money.dollars(BigDecimal.valueOf(1.01)), 5, null)));
        assertFalse(addItemToShopResponse.hasErrors());
        addItemToShopResponse =
                addItemToShopService.execute(new AddItemToShopRequest(new ItemDTO(null, "new item 2", Money.dollars(BigDecimal.valueOf(7.07)), 3, null)));
        assertFalse(addItemToShopResponse.hasErrors());
        List<Item> shopItems = itemRepository.findAll();
        assertEquals(2, shopItems.size() - shopItemCountBefore);
        assertTrue(itemRepository.findByName("new item 1").isPresent());
        Item newItem1 = itemRepository.findByName("new item 1").get();
        assertEquals(new BigDecimal("1.01"), newItem1.getPrice());
        assertEquals(5, newItem1.getAvailableQuantity());
        assertTrue(itemRepository.findByName("new item 2").isPresent());
        Item newItem2 = itemRepository.findByName("new item 2").get();
        assertEquals(new BigDecimal("7.07"), newItem2.getPrice());
        assertEquals(3, newItem2.getAvailableQuantity());
    }

}
