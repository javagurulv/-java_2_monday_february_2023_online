package shop.console_ui.item_list;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import shop.console_ui.UserCommunication;
import shop.core.domain.item.Item_;
import shop.core.support.ordering.OrderingRule;

import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
class OrderingUIElementTest {

    @Mock
    private UserCommunication mockUserCommunication;

    @InjectMocks
    private OrderingUIElement orderingUIElement;

    @Test
    void shouldReturnEmptyOrderingRulesList() {
        when(mockUserCommunication.requestInput(anyString())).thenReturn("");
        List<OrderingRule> result = orderingUIElement.getOrderingRules();
        assertTrue(result.isEmpty());
    }

    @Test
    void shouldReturnOrderingRulesListWithName() {
        when(mockUserCommunication.requestInput(anyString())).thenReturn("y", "");
        List<OrderingRule> result = orderingUIElement.getOrderingRules();
        assertEquals(1, result.size());
        assertEquals(Item_.name, result.get(0).getOrderBy());
        assertEquals(true, result.get(0).isAscending());
    }

    @Test
    void shouldReturnOrderingRulesListWithPrice() {
        when(mockUserCommunication.requestInput(anyString())).thenReturn("", "y", "");
        List<OrderingRule> result = orderingUIElement.getOrderingRules();
        assertEquals(1, result.size());
        assertEquals(Item_.price, result.get(0).getOrderBy());
        assertEquals(true, result.get(0).isAscending());
    }

    @Test
    void shouldReturnOrderingRulesListWithNameAndPrice() {
        when(mockUserCommunication.requestInput(anyString())).thenReturn("y", "", "y", "");
        List<OrderingRule> result = orderingUIElement.getOrderingRules();
        assertEquals(2, result.size());
        assertEquals(Item_.name, result.get(0).getOrderBy());
        assertEquals(true, result.get(0).isAscending());
        assertEquals(Item_.price, result.get(1).getOrderBy());
        assertEquals(true, result.get(1).isAscending());
    }

    @Test
    void shouldReturnOrderingRulesListWithNameDescending() {
        when(mockUserCommunication.requestInput(anyString())).thenReturn("y", "y", "");
        List<OrderingRule> result = orderingUIElement.getOrderingRules();
        assertEquals(1, result.size());
        assertEquals(Item_.name, result.get(0).getOrderBy());
        assertEquals(false, result.get(0).isAscending());
    }

    @Test
    void shouldReturnOrderingRulesListWithPriceDescending() {
        when(mockUserCommunication.requestInput(anyString())).thenReturn("", "y", "y");
        List<OrderingRule> result = orderingUIElement.getOrderingRules();
        assertEquals(1, result.size());
        assertEquals(Item_.price, result.get(0).getOrderBy());
        assertEquals(false, result.get(0).isAscending());
    }

    @Test
    void shouldReturnOrderingRulesListWithNameDescendingAndPrice() {
        when(mockUserCommunication.requestInput(anyString())).thenReturn("y", "y", "y", "");
        List<OrderingRule> result = orderingUIElement.getOrderingRules();
        assertEquals(2, result.size());
        assertEquals(Item_.name, result.get(0).getOrderBy());
        assertEquals(false, result.get(0).isAscending());
        assertEquals(Item_.price, result.get(1).getOrderBy());
        assertEquals(true, result.get(1).isAscending());
    }

    @Test
    void shouldReturnOrderingRulesListWithNameAndPriceDescending() {
        when(mockUserCommunication.requestInput(anyString())).thenReturn("y", "", "y", "y");
        List<OrderingRule> result = orderingUIElement.getOrderingRules();
        assertEquals(2, result.size());
        assertEquals(Item_.name, result.get(0).getOrderBy());
        assertEquals(true, result.get(0).isAscending());
        assertEquals(Item_.price, result.get(1).getOrderBy());
        assertEquals(false, result.get(1).isAscending());
    }

    @Test
    void shouldReturnOrderingRulesListWithNameAndPriceBothDescending() {
        when(mockUserCommunication.requestInput(anyString())).thenReturn("y");
        List<OrderingRule> result = orderingUIElement.getOrderingRules();
        assertEquals(2, result.size());
        assertEquals(Item_.name, result.get(0).getOrderBy());
        assertEquals(false, result.get(0).isAscending());
        assertEquals(Item_.price, result.get(1).getOrderBy());
        assertEquals(false, result.get(1).isAscending());
    }

    @Test
    void shouldAcceptCapitalLetters() {
        when(mockUserCommunication.requestInput(anyString())).thenReturn("Y", "Y", "");
        List<OrderingRule> result = orderingUIElement.getOrderingRules();
        assertEquals(1, result.size());
        assertEquals(Item_.name, result.get(0).getOrderBy());
        assertEquals(false, result.get(0).isAscending());
    }

}
