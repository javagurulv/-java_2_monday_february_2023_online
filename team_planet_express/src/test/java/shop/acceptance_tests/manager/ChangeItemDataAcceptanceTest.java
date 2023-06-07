package shop.acceptance_tests.manager;

import org.junit.Ignore;
import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.jdbc.Sql;
import org.springframework.test.context.junit4.SpringRunner;
import shop.core.database.ItemRepository;
import shop.core.domain.item.Item;
import shop.core.services.actions.manager.ChangeItemDataServiceImpl;
import shop.core_api.requests.manager.ChangeItemDataRequest;
import shop.core_api.responses.CoreError;
import shop.core_api.responses.manager.ChangeItemDataResponse;

import java.math.BigDecimal;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

@RunWith(SpringRunner.class)
@SpringBootTest
public class ChangeItemDataAcceptanceTest {

    @Autowired
    private ItemRepository itemRepository;
    @Autowired
    private ChangeItemDataServiceImpl changeItemDataService;

    @Sql({"/testDatabaseTableCreation.sql", "/testDatabaseDataInsertion.sql"})
    @Test
    @Ignore
    void shouldChangeItemNameOnly() {
        ChangeItemDataResponse changeItemDataResponse =
                changeItemDataService.execute(new ChangeItemDataRequest("6", "Delivery-Boy Man", "", ""));
        assertFalse(changeItemDataResponse.hasErrors());
        Item item = itemRepository.findById(6L).orElseThrow();
        assertCorrectItemChanges(item, "Delivery-Boy Man", new BigDecimal("24.99"), 70);
    }

    @Sql({"/testDatabaseTableCreation.sql", "/testDatabaseDataInsertion.sql"})
    @Test
    @Ignore
    void shouldChangePriceOnly() {
        ChangeItemDataResponse changeItemDataResponse =
                changeItemDataService.execute(new ChangeItemDataRequest("10", "", "1000.00", ""));
        assertFalse(changeItemDataResponse.hasErrors());
        Item item = itemRepository.findById(10L).orElseThrow();
        assertCorrectItemChanges(item, "Popplers", new BigDecimal("1000.00"), 0);
    }

    @Sql({"/testDatabaseTableCreation.sql", "/testDatabaseDataInsertion.sql"})
    @Test
    @Ignore
    void shouldChangeQuantityOnly() {
        ChangeItemDataResponse changeItemDataResponse =
                changeItemDataService.execute(new ChangeItemDataRequest("1", "", "", "0"));
        assertFalse(changeItemDataResponse.hasErrors());
        Item item = itemRepository.findById(1L).orElseThrow();
        assertCorrectItemChanges(item, "Stop-and-Drop Suicide Booth", new BigDecimal("1000.00"), 0);
    }

    @Sql({"/testDatabaseTableCreation.sql", "/testDatabaseDataInsertion.sql"})
    @Test
    @Ignore
    void shouldChangeEverything() {
        ChangeItemDataResponse changeItemDataResponse =
                changeItemDataService.execute(new ChangeItemDataRequest("2", "Good News Everyone", "7.77", "100"));
        assertFalse(changeItemDataResponse.hasErrors());
        Item item = itemRepository.findById(2L).orElseThrow();
        assertCorrectItemChanges(item, "Good News Everyone", new BigDecimal("7.77"), 100);
    }

    @Sql({"/testDatabaseTableCreation.sql", "/testDatabaseDataInsertion.sql"})
    @Test
    @Ignore
    void shouldReturnErrorForDuplicate() {
        ChangeItemDataResponse changeItemDataResponse =
                changeItemDataService.execute(new ChangeItemDataRequest("4", "Lightspeed Briefs", "249.99", "50"));
        assertTrue(changeItemDataResponse.hasErrors());
        List<CoreError> errors = changeItemDataResponse.getErrors();
        assertEquals(1, errors.size());
        assertEquals("Error: Exactly the same item already exists.", errors.get(0).getMessage());
    }

    @Sql({"/testDatabaseTableCreation.sql", "/testDatabaseDataInsertion.sql"})
    @Test
    @Ignore
    void shouldReturnErrorForDuplicate2() {
        Item item = new Item("Angry Norwegian Anchovies", new BigDecimal("249.99"), 1);
        itemRepository.save(item);
        ChangeItemDataResponse changeItemDataResponse =
                changeItemDataService.execute(new ChangeItemDataRequest("11", "Lightspeed Briefs", "", ""));
        assertTrue(changeItemDataResponse.hasErrors());
        List<CoreError> errors = changeItemDataResponse.getErrors();
        assertEquals(1, errors.size());
        assertEquals("Error: Exactly the same item already exists.", errors.get(0).getMessage());
    }

    private void assertCorrectItemChanges(Item item, String itemName, BigDecimal price, Integer quantity) {
        assertEquals(itemName, item.getName());
        assertEquals(price, item.getPrice());
        assertEquals(quantity, item.getAvailableQuantity());
    }

}
