package shop.acceptance_tests.customer;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.jdbc.Sql;
import shop.core.database.ItemRepository;
import shop.core.database.UserRepository;
import shop.core.domain.Item;
import shop.core.domain.User;
import shop.core.dtos.ItemDto;
import shop.core.requests.shared.SearchItemRequest;
import shop.core.responses.shared.SearchItemResponse;
import shop.core.services.actions.shared.SearchItemService;
import shop.core.support.CurrentUserId;
import shop.core.support.ordering.OrderBy;
import shop.core.support.ordering.OrderDirection;
import shop.core.support.ordering.OrderingRule;
import shop.core.support.paging.PagingRule;

import java.math.BigDecimal;
import java.util.Collections;
import java.util.List;
import java.util.Optional;
import java.util.stream.IntStream;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
public class SearchForItemsAcceptanceTest {

    @Autowired
    private UserRepository userRepository;
    @Autowired
    protected ItemRepository itemRepository;
    @Autowired
    private CurrentUserId currentUserId;
    @Autowired
    private SearchItemService searchItemService;

    @BeforeEach
    void setCurrentUser() {
        User currentUser = userRepository.findById(1L).orElseThrow();
        this.currentUserId.setValue(currentUser.getId());
    }

    @Sql({"/testDatabaseTableCreation.sql", "/testDatabaseDataInsertion.sql"})
    @Test
    void shouldReturnAllItemsWithRobotInTheName() {
        SearchItemResponse searchItemResponse =
                searchItemService.execute(new SearchItemRequest(currentUserId, "robot", "", Collections.emptyList(), null));
        assertFalse(searchItemResponse.hasErrors());
        assertFalse(searchItemResponse.isNextPageAvailable());
        assertEquals("Moms Old-Fashioned Robot Oil", searchItemResponse.getItems().get(0).getName());
        assertEquals("Blank Robot", searchItemResponse.getItems().get(1).getName());
    }

    @Sql({"/testDatabaseTableCreation.sql", "/testDatabaseDataInsertion.sql"})
    @Test
    void shouldReturnAllItemsCheaperThan10() {
        SearchItemResponse searchItemResponse =
                searchItemService.execute(new SearchItemRequest(currentUserId, "", "10", Collections.emptyList(), null));
        assertFalse(searchItemResponse.hasErrors());
        assertFalse(searchItemResponse.isNextPageAvailable());
        Optional<ItemDto> wrongItem = searchItemResponse.getItems().stream()
                .filter(item -> new BigDecimal("10").compareTo(item.getPrice()) < 0)
                .findAny();
        assertTrue(wrongItem.isEmpty());
    }

    @Sql({"/testDatabaseTableCreation.sql", "/testDatabaseDataInsertion.sql"})
    @Test
    void shouldOrderRobotItemsAscending() {
        OrderingRule orderingRule = new OrderingRule(OrderBy.NAME, OrderDirection.ASCENDING);
        SearchItemResponse searchItemResponse =
                searchItemService.execute(new SearchItemRequest(currentUserId, "robot", "", List.of(orderingRule), null));
        assertFalse(searchItemResponse.hasErrors());
        assertFalse(searchItemResponse.isNextPageAvailable());
        assertTrue(isOrderedCorrectly(searchItemResponse.getItems(), 7L, 4L));
    }

    @Sql({"/testDatabaseTableCreation.sql", "/testDatabaseDataInsertion.sql"})
    @Test
    void shouldReturnSecondThreeItemPage() {
        PagingRule pagingRule = new PagingRule(2, "3");
        SearchItemResponse searchItemResponse =
                searchItemService.execute(new SearchItemRequest(currentUserId, "", "", Collections.emptyList(), pagingRule));
        assertFalse(searchItemResponse.hasErrors());
        assertEquals(3, searchItemResponse.getItems().size());
        assertTrue(searchItemResponse.isNextPageAvailable());
        assertTrue(isPageContainingCorrectItems(searchItemResponse.getItems(), 4L, 5L, 6L));
    }

    @Sql({"/testDatabaseTableCreation.sql", "/testDatabaseDataInsertion.sql"})
    @Test
    void shouldReturnCorrectItemsInCorrectOrder() {
        Item newItem = new Item("Morbo on Management", new BigDecimal("4.99"), 1);
        itemRepository.save(newItem);
        OrderingRule orderingRuleName = new OrderingRule(OrderBy.NAME, OrderDirection.DESCENDING);
        OrderingRule orderingRulePrice = new OrderingRule(OrderBy.PRICE, OrderDirection.ASCENDING);
        PagingRule pagingRule = new PagingRule(1, "4");
        SearchItemResponse searchItemResponse =
                searchItemService.execute(new SearchItemRequest(currentUserId, "T", "25", List.of(orderingRuleName, orderingRulePrice), pagingRule));
        assertFalse(searchItemResponse.hasErrors());
        assertTrue(searchItemResponse.isNextPageAvailable());
        Optional<ItemDto> wrongItem = searchItemResponse.getItems().stream()
                .filter(item -> new BigDecimal("25").compareTo(item.getPrice()) < 0)
                .findAny();
        assertTrue(wrongItem.isEmpty());
        assertTrue(isOrderedCorrectly(searchItemResponse.getItems(), 11L, 6L, 4L, 9L));
        assertEquals(4, searchItemResponse.getItems().size());
        pagingRule = new PagingRule(2, "4");
        searchItemResponse =
                searchItemService.execute(new SearchItemRequest(currentUserId, "T", "25", List.of(orderingRuleName, orderingRulePrice), pagingRule));
        assertEquals(1, searchItemResponse.getItems().size());
        assertTrue(isPageContainingCorrectItems(searchItemResponse.getItems(), 7L));
    }

    private boolean isOrderedCorrectly(List<ItemDto> items, Long... ids) {
        return IntStream.range(0, ids.length)
                .allMatch(index -> ids[index].equals(items.get(index).getId()));
    }

    private boolean isPageContainingCorrectItems(List<ItemDto> items, Long... ids) {
        return IntStream.range(0, ids.length)
                .allMatch(index -> ids[index].equals(items.get(index).getId()));
    }

}
