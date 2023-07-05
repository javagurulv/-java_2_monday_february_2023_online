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
import shop.core.services.actions.manager.ChangeItemDataServiceImpl;
import shop.core_api.dto.item.ItemDTO;
import shop.core_api.dto.item.Money;
import shop.core_api.requests.manager.ChangeItemDataRequest;
import shop.core_api.responses.CoreError;
import shop.core_api.responses.manager.ChangeItemDataResponse;

import java.math.BigDecimal;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

@RunWith(SpringRunner.class)
@SpringBootTest
@Transactional
public class ChangeItemDataAcceptanceTest {

    @Autowired
    private ItemRepository itemRepository;
    @Autowired
    private ChangeItemDataServiceImpl changeItemDataService;

    @Sql({"/testDatabaseTableCreation.sql", "/testDatabaseDataInsertion.sql"})
    @Test
    @Ignore
    void shouldChangeItemNameOnly() {
        ItemDTO itemDTO = new ItemDTO(6L, "Delivery-Boy Man", null, null, null);
        ChangeItemDataResponse changeItemDataResponse =
                changeItemDataService.execute(new ChangeItemDataRequest(itemDTO));
        assertFalse(changeItemDataResponse.hasErrors());
        Item item = itemRepository.findById(6L).orElseThrow();
        assertCorrectItemChanges(item, "Delivery-Boy Man", new BigDecimal("24.99"), 70);
    }

    @Sql({"/testDatabaseTableCreation.sql", "/testDatabaseDataInsertion.sql"})
    @Test
    @Ignore
    void shouldChangePriceOnly() {
        ItemDTO itemDTO = new ItemDTO(10L, "Popplers", Money.dollars(BigDecimal.valueOf(1000.00)), 0, null);
        ChangeItemDataResponse changeItemDataResponse =
                changeItemDataService.execute(new ChangeItemDataRequest(itemDTO));
        assertFalse(changeItemDataResponse.hasErrors());
        Item item = itemRepository.findById(10L).orElseThrow();
        assertCorrectItemChanges(item, "Popplers", new BigDecimal("1000.00"), 0);
    }

    @Sql({"/testDatabaseTableCreation.sql", "/testDatabaseDataInsertion.sql"})
    @Test
    @Ignore
    void shouldChangeQuantityOnly() {
        ItemDTO itemDTO = new ItemDTO(1L, null, null, 0, null);
        ChangeItemDataResponse changeItemDataResponse =
                changeItemDataService.execute(new ChangeItemDataRequest(itemDTO));
        assertFalse(changeItemDataResponse.hasErrors());
        Item item = itemRepository.findById(1L).orElseThrow();
        assertCorrectItemChanges(item, "Stop-and-Drop Suicide Booth", new BigDecimal("1000.00"), 0);
    }

    @Sql({"/testDatabaseTableCreation.sql", "/testDatabaseDataInsertion.sql"})
    @Test
    @Ignore
    void shouldChangeEverything() {
        ItemDTO itemDTO = new ItemDTO(2L, "Good News Everyone", Money.dollars(BigDecimal.valueOf(7.77)), 100, null);
        ChangeItemDataResponse changeItemDataResponse =
                changeItemDataService.execute(new ChangeItemDataRequest(itemDTO));
        assertFalse(changeItemDataResponse.hasErrors());
        Item item = itemRepository.findById(2L).orElseThrow();
        assertCorrectItemChanges(item, "Good News Everyone", new BigDecimal("7.77"), 100);
    }

    @Sql({"/testDatabaseTableCreation.sql", "/testDatabaseDataInsertion.sql"})
    @Test
    @Ignore
    void shouldReturnErrorForDuplicate() {
        ItemDTO itemDTO = new ItemDTO(4L, "Lightspeed Briefs", Money.dollars(BigDecimal.valueOf(249.99)), 50, null);
        ChangeItemDataResponse changeItemDataResponse =
                changeItemDataService.execute(new ChangeItemDataRequest(itemDTO));
        assertTrue(changeItemDataResponse.hasErrors());
        List<CoreError> errors = changeItemDataResponse.getErrors();
        assertEquals(1, errors.size());
        assertEquals("Error: Exactly the same item already exists.", errors.get(0).getMessage());
    }

    @Sql({"/testDatabaseTableCreation.sql", "/testDatabaseDataInsertion.sql"})
    @Test
    @Ignore
    void shouldReturnErrorForDuplicate2() {
        ItemDTO itemDTO = new ItemDTO(11L, "Lightspeed Briefs", null, null, null);
        Item item = new Item("Angry Norwegian Anchovies", new BigDecimal("249.99"), 1);
        itemRepository.save(item);
        ChangeItemDataResponse changeItemDataResponse =
                changeItemDataService.execute(new ChangeItemDataRequest(itemDTO));
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
