package shop.core.services.item_list;

import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.test.util.ReflectionTestUtils;
import shop.core.domain.Item;
import shop.core.support.ordering.OrderBy;
import shop.core.support.ordering.OrderDirection;
import shop.core.support.ordering.OrderingRule;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.IntStream;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

class OrderingServiceTest {

    private static final Item item1 = new Item("aaa", new BigDecimal("10"), 10);
    private static final Item item2 = new Item("zzz", new BigDecimal("20"), 10);
    private static final Item item3 = new Item("aaa", new BigDecimal("20"), 10);
    private static final Item item4 = new Item("zzz", new BigDecimal("10"), 10);
    private static final Item item5 = new Item("UwU", new BigDecimal("17"), 10);

    private List<Item> items;

    private final OrderingService service = new OrderingService();

    @BeforeAll
    static void setupItemId() {
        item1.setId(1L);
        item2.setId(2L);
        item3.setId(3L);
        item4.setId(4L);
        item5.setId(5L);
    }

    @BeforeEach
    void setupItemList() {
        ReflectionTestUtils.setField(service, "orderingEnabled", true);
        items = new ArrayList<>();
        items.add(item1);
        items.add(item2);
        items.add(item3);
        items.add(item4);
        items.add(item5);
    }

    @Test
    void shouldNotOrderForNullOrderingRules() {
        List<OrderingRule> orderingRules = null;
        items = service.getOrderedItems(items, orderingRules);
        assertTrue(isOrderedCorrectly(1L, 2L, 3L, 4L, 5L));
    }

    @Test
    void shouldNotOrderForEmptyOrderingRules() {
        List<OrderingRule> orderingRules = new ArrayList<>();
        items = service.getOrderedItems(items, orderingRules);
        assertTrue(isOrderedCorrectly(1L, 2L, 3L, 4L, 5L));
    }

    @Test
    void shouldOrderByName() {
        OrderingRule orderingRule = new OrderingRule(OrderBy.NAME, OrderDirection.ASCENDING);
        List<OrderingRule> orderingRules = List.of(orderingRule);
        items = service.getOrderedItems(items, orderingRules);
        assertTrue(isOrderedCorrectly(1L, 3L, 5L, 2L, 4L));
    }

    @Test
    void shouldOrderByNameDescending() {
        OrderingRule orderingRule = new OrderingRule(OrderBy.NAME, OrderDirection.DESCENDING);
        List<OrderingRule> orderingRules = List.of(orderingRule);
        items = service.getOrderedItems(items, orderingRules);
        assertTrue(isOrderedCorrectly(2L, 4L, 5L, 1L, 3L));
    }

    @Test
    void shouldOrderByPrice() {
        OrderingRule orderingRule = new OrderingRule(OrderBy.PRICE, OrderDirection.ASCENDING);
        List<OrderingRule> orderingRules = List.of(orderingRule);
        items = service.getOrderedItems(items, orderingRules);
        assertTrue(isOrderedCorrectly(1L, 4L, 5L, 2L, 3L));
    }

    @Test
    void shouldOrderByPriceDescending() {
        OrderingRule orderingRule = new OrderingRule(OrderBy.PRICE, OrderDirection.DESCENDING);
        List<OrderingRule> orderingRules = List.of(orderingRule);
        items = service.getOrderedItems(items, orderingRules);
        assertTrue(isOrderedCorrectly(2L, 3L, 5L, 1L, 4L));
    }

    @Test
    void shouldOrderByNameAndPrice() {
        OrderingRule orderingRuleName = new OrderingRule(OrderBy.NAME, OrderDirection.ASCENDING);
        OrderingRule orderingRulePrice = new OrderingRule(OrderBy.PRICE, OrderDirection.ASCENDING);
        List<OrderingRule> orderingRules = List.of(orderingRuleName, orderingRulePrice);
        items = service.getOrderedItems(items, orderingRules);
        assertTrue(isOrderedCorrectly(1L, 3L, 5L, 4L, 2L));
    }

    @Test
    void shouldOrderByNameDescendingAndPrice() {
        OrderingRule orderingRuleName = new OrderingRule(OrderBy.NAME, OrderDirection.DESCENDING);
        OrderingRule orderingRulePrice = new OrderingRule(OrderBy.PRICE, OrderDirection.ASCENDING);
        List<OrderingRule> orderingRules = List.of(orderingRuleName, orderingRulePrice);
        items = service.getOrderedItems(items, orderingRules);
        assertTrue(isOrderedCorrectly(4L, 2L, 5L, 1L, 3L));
    }

    @Test
    void shouldOrderByNameAndPriceDescending() {
        OrderingRule orderingRuleName = new OrderingRule(OrderBy.NAME, OrderDirection.ASCENDING);
        OrderingRule orderingRulePrice = new OrderingRule(OrderBy.PRICE, OrderDirection.DESCENDING);
        List<OrderingRule> orderingRules = List.of(orderingRuleName, orderingRulePrice);
        items = service.getOrderedItems(items, orderingRules);
        assertTrue(isOrderedCorrectly(3L, 1L, 5L, 2L, 4L));
    }

    @Test
    void shouldOrderByNameDescendingAndPriceDescending() {
        OrderingRule orderingRuleName = new OrderingRule(OrderBy.NAME, OrderDirection.DESCENDING);
        OrderingRule orderingRulePrice = new OrderingRule(OrderBy.PRICE, OrderDirection.DESCENDING);
        List<OrderingRule> orderingRules = List.of(orderingRuleName, orderingRulePrice);
        items = service.getOrderedItems(items, orderingRules);
        assertTrue(isOrderedCorrectly(2L, 4L, 5L, 3L, 1L));
    }

    @Test
    void shouldReturnBlankForDisabled() {
        ReflectionTestUtils.setField(service, "orderingEnabled", false);
        OrderingRule orderingRuleName = new OrderingRule(OrderBy.NAME, OrderDirection.DESCENDING);
        OrderingRule orderingRulePrice = new OrderingRule(OrderBy.PRICE, OrderDirection.DESCENDING);
        List<OrderingRule> orderingRules = List.of(orderingRuleName, orderingRulePrice);
        String actualResult = service.getSQLOrderBy(orderingRules);
        String expectedResult = "";
        assertEquals(expectedResult, actualResult);
    }

    @Test
    void shouldReturnBlankForNullOrderingRules() {
        List<OrderingRule> orderingRules = null;
        String actualResult = service.getSQLOrderBy(orderingRules);
        String expectedResult = "";
        assertEquals(expectedResult, actualResult);

    }

    @Test
    void shouldReturnOrderByNameAscending() {
        OrderingRule orderingRule = new OrderingRule(OrderBy.NAME, OrderDirection.ASCENDING);
        List<OrderingRule> orderingRules = List.of(orderingRule);
        String actualResult = service.getSQLOrderBy(orderingRules);
        String expectedResult = "ORDER BY name ASC";
        assertEquals(expectedResult, actualResult);
    }

    @Test
    void shouldReturnOrderByPriceDescending() {
        OrderingRule orderingRule = new OrderingRule(OrderBy.PRICE, OrderDirection.DESCENDING);
        List<OrderingRule> orderingRules = List.of(orderingRule);
        String actualResult = service.getSQLOrderBy(orderingRules);
        String expectedResult = "ORDER BY price DESC";
        assertEquals(expectedResult, actualResult);
    }

    @Test
    void shouldReturnOrderByPriceAndName() {
        OrderingRule orderingRuleName = new OrderingRule(OrderBy.NAME, OrderDirection.DESCENDING);
        OrderingRule orderingRulePrice = new OrderingRule(OrderBy.PRICE, OrderDirection.ASCENDING);
        List<OrderingRule> orderingRules = List.of(orderingRuleName, orderingRulePrice);
        String actualResult = service.getSQLOrderBy(orderingRules);
        String expectedResult = "ORDER BY name DESC, price ASC";
        assertEquals(expectedResult, actualResult);
    }

    private boolean isOrderedCorrectly(Long... ids) {
        return IntStream.range(0, ids.length)
                .allMatch(index -> ids[index].equals(items.get(index).getId()));
    }

}
