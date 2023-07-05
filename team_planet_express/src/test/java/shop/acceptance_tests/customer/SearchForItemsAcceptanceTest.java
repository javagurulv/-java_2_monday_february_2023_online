package shop.acceptance_tests.customer;

import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.security.test.context.support.WithUserDetails;
import org.springframework.test.context.jdbc.Sql;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.transaction.annotation.Transactional;
import shop.core.database.ItemRepository;
import shop.core.domain.item.Item;
import shop.core.domain.item.ItemConverter;
import shop.core.services.actions.shared.SearchItemServiceImpl;
import shop.core.support.ordering.OrderBy;
import shop.core.support.ordering.OrderingRule;
import shop.core.support.paging.PagingRule;
import shop.core_api.dto.item.ItemDTO;
import shop.core_api.requests.shared.SearchItemRequest;
import shop.core_api.responses.shared.SearchItemResponse;

import java.math.BigDecimal;
import java.util.Collections;
import java.util.List;
import java.util.Optional;
import java.util.stream.IntStream;

import static org.junit.jupiter.api.Assertions.*;

@RunWith(SpringRunner.class)
@SpringBootTest
@Transactional
public class SearchForItemsAcceptanceTest {

    @Autowired
    protected ItemRepository itemRepository;
    @Autowired
    private SearchItemServiceImpl searchItemService;

    @Sql({"/testDatabaseTableCreation.sql", "/testDatabaseDataInsertion.sql"})
    @Test
    @WithUserDetails("customer")
    void shouldReturnAllItemsWithRobotInTheName() {
        PagingRule pagingRule = new PagingRule(0, 10);
        SearchItemResponse searchItemResponse =
                searchItemService.execute(new SearchItemRequest("robot", null, null, Collections.emptyList(), pagingRule));
        assertFalse(searchItemResponse.hasErrors());
        assertFalse(searchItemResponse.isNextPageAvailable());
        assertEquals("Moms Old-Fashioned Robot Oil", searchItemResponse.getItemsDTO().get(0).getName());
        assertEquals("Blank Robot", searchItemResponse.getItemsDTO().get(1).getName());
    }

    @Sql({"/testDatabaseTableCreation.sql", "/testDatabaseDataInsertion.sql"})
    @Test
    @WithUserDetails("customer")
    void shouldReturnAllItemsCheaperThan10() {
        PagingRule pagingRule = new PagingRule(0, 10);
        SearchItemResponse searchItemResponse =
                searchItemService.execute(new SearchItemRequest("", 0, 10, Collections.emptyList(), pagingRule));
        assertFalse(searchItemResponse.hasErrors());
        assertFalse(searchItemResponse.isNextPageAvailable());
        Optional<ItemDTO> wrongItem = searchItemResponse.getItemsDTO().stream()
                .filter(item -> new BigDecimal("10").compareTo(item.getPrice().getAmount()) < 0)
                .findAny();
        assertTrue(wrongItem.isEmpty());
    }

    @Sql({"/testDatabaseTableCreation.sql", "/testDatabaseDataInsertion.sql"})
    @Test
    @WithUserDetails("customer")
    void shouldOrderRobotItemsAscending() {
        PagingRule pagingRule = new PagingRule(0, 10);
        OrderingRule orderingRule = new OrderingRule(OrderBy.NAME, true);
        SearchItemResponse searchItemResponse =
                searchItemService.execute(new SearchItemRequest("robot", 0, null, List.of(orderingRule), pagingRule));
        assertFalse(searchItemResponse.hasErrors());
        assertFalse(searchItemResponse.isNextPageAvailable());
        assertTrue(isOrderedCorrectly(searchItemResponse.getItemsDTO().stream().map(itemDTO -> ItemConverter.toItem(itemDTO)).toList(), 7L, 4L));
    }

    @Sql({"/testDatabaseTableCreation.sql", "/testDatabaseDataInsertion.sql"})
    @Test
    @WithUserDetails("customer")
    void shouldReturnSecondThreeItemPage() {
        PagingRule pagingRule = new PagingRule(1, 3);
        SearchItemResponse searchItemResponse =
                searchItemService.execute(new SearchItemRequest("", 0, null, Collections.emptyList(), pagingRule));
        assertFalse(searchItemResponse.hasErrors());
        assertEquals(3, searchItemResponse.getItemsDTO().size());
        assertTrue(searchItemResponse.isNextPageAvailable());
        assertTrue(isPageContainingCorrectItems(searchItemResponse.getItemsDTO().stream().map(itemDTO -> ItemConverter.toItem(itemDTO)).toList(), 4L, 5L, 6L));
    }

    @Sql({"/testDatabaseTableCreation.sql", "/testDatabaseDataInsertion.sql"})
    @Test
    @WithUserDetails("customer")
    void shouldReturnCorrectItemsInCorrectOrder() {
        Item newItem = new Item("Morbo on Management", new BigDecimal("4.99"), 1);
        itemRepository.save(newItem);
        OrderingRule orderingRuleName = new OrderingRule(OrderBy.NAME, false);
        OrderingRule orderingRulePrice = new OrderingRule(OrderBy.PRICE, true);
        PagingRule pagingRule = new PagingRule(0, 4);
        SearchItemResponse searchItemResponse =
                searchItemService.execute(new SearchItemRequest("T", 0, 25, List.of(orderingRuleName, orderingRulePrice), pagingRule));
        assertFalse(searchItemResponse.hasErrors());
        assertTrue(searchItemResponse.isNextPageAvailable());
        Optional<ItemDTO> wrongItem = searchItemResponse.getItemsDTO().stream()
                .filter(item -> new BigDecimal("25").compareTo(item.getPrice().getAmount()) < 0)
                .findAny();
        assertTrue(wrongItem.isEmpty());
        assertTrue(isOrderedCorrectly(searchItemResponse.getItemsDTO().stream().map(itemDTO -> ItemConverter.toItem(itemDTO)).toList(), 11L, 6L, 4L, 9L));
        assertEquals(4, searchItemResponse.getItemsDTO().size());
        pagingRule = new PagingRule(1, 4);
        searchItemResponse =
                searchItemService.execute(new SearchItemRequest("T", 0, 25, List.of(orderingRuleName, orderingRulePrice), pagingRule));
        assertEquals(1, searchItemResponse.getItemsDTO().size());
        assertTrue(isPageContainingCorrectItems(searchItemResponse.getItemsDTO().stream().map(itemDTO -> ItemConverter.toItem(itemDTO)).toList(), 7L));
    }

    private boolean isOrderedCorrectly(List<Item> items, Long... ids) {
        return IntStream.range(0, ids.length)
                .allMatch(index -> ids[index].equals(items.get(index).getId()));
    }

    private boolean isPageContainingCorrectItems(List<Item> items, Long... ids) {
        return IntStream.range(0, ids.length)
                .allMatch(index -> ids[index].equals(items.get(index).getId()));
    }

}
