package shop.acceptance_tests.manager;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.jdbc.Sql;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import shop.config.ShopConfiguration;
import shop.core.database.Repository;
import shop.core.domain.item.Item;
import shop.core.requests.manager.AddItemToShopRequest;
import shop.core.responses.manager.AddItemToShopResponse;
import shop.core.services.actions.manager.AddItemToShopService;

import java.math.BigDecimal;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

@ExtendWith(SpringExtension.class)
@ContextConfiguration(classes = {ShopConfiguration.class})
public class AddItemsToTheShopAcceptanceTest {

    @Autowired
    private Repository repository;
    @Autowired
    private AddItemToShopService addItemToShopService;

    @Sql({"/testDatabaseTableCreation.sql", "/testDatabaseDataInsertion.sql"})
    @Test
    void shouldAddItemsToTheShop() {
        int shopItemCountBefore = repository.accessItemDatabase().getAllItems().size();
        AddItemToShopResponse addItemToShopResponse =
                addItemToShopService.execute(new AddItemToShopRequest("new item 1", "1.01", "5"));
        assertFalse(addItemToShopResponse.hasErrors());
        addItemToShopResponse =
                addItemToShopService.execute(new AddItemToShopRequest("new item 2", "7.07", "3"));
        assertFalse(addItemToShopResponse.hasErrors());
        List<Item> shopItems = repository.accessItemDatabase().getAllItems();
        assertEquals(2, shopItems.size() - shopItemCountBefore);
        assertTrue(repository.accessItemDatabase().findByName("new item 1").isPresent());
        Item newItem1 = repository.accessItemDatabase().findByName("new item 1").get();
        assertEquals(new BigDecimal("1.01"), newItem1.getPrice());
        assertEquals(5, newItem1.getAvailableQuantity());
        assertTrue(repository.accessItemDatabase().findByName("new item 2").isPresent());
        Item newItem2 = repository.accessItemDatabase().findByName("new item 2").get();
        assertEquals(new BigDecimal("7.07"), newItem2.getPrice());
        assertEquals(3, newItem2.getAvailableQuantity());
    }

}
