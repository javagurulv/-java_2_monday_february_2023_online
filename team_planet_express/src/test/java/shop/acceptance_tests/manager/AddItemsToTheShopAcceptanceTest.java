package shop.acceptance_tests.manager;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.jdbc.Sql;
import shop.core.database.jpa.JpaItemRepository;
import shop.core.domain.Item;
import shop.core.requests.manager.AddItemToShopRequest;
import shop.core.responses.manager.AddItemToShopResponse;
import shop.core.services.actions.manager.AddItemToShopService;

import java.math.BigDecimal;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
public class AddItemsToTheShopAcceptanceTest {

    @Autowired
    private JpaItemRepository itemRepository;
    @Autowired
    private AddItemToShopService addItemToShopService;

    @Sql({"/testDatabaseTableCreation.sql", "/testDatabaseDataInsertion.sql"})
    @Test
    void shouldAddItemsToTheShop() {
        int shopItemCountBefore = itemRepository.findAll().size();
        AddItemToShopResponse addItemToShopResponse =
                addItemToShopService.execute(new AddItemToShopRequest("new item 1", "1.01", "5"));
        assertFalse(addItemToShopResponse.hasErrors());
        addItemToShopResponse =
                addItemToShopService.execute(new AddItemToShopRequest("new item 2", "7.07", "3"));
        assertFalse(addItemToShopResponse.hasErrors());
        List<Item> shopItems = itemRepository.findAll();
        assertEquals(2, shopItems.size() - shopItemCountBefore);
        assertTrue(itemRepository.findByName("new item 1").stream().findFirst().isPresent());
        Item newItem1 = itemRepository.findByName("new item 1").stream().findFirst().get();
        assertEquals(new BigDecimal("1.01"), newItem1.getPrice());
        assertEquals(5, newItem1.getAvailableQuantity());
        assertTrue(itemRepository.findByName("new item 2").stream().findFirst().isPresent());
        Item newItem2 = itemRepository.findByName("new item 2").stream().findFirst().get();
        assertEquals(new BigDecimal("7.07"), newItem2.getPrice());
        assertEquals(3, newItem2.getAvailableQuantity());
    }

}
