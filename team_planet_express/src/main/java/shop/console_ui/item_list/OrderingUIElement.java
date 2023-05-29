package shop.console_ui.item_list;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import shop.console_ui.UserCommunication;
import shop.core.domain.item.Item_;
import shop.core.support.ordering.OrderingRule;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Component
public class OrderingUIElement {

    private static final String PROMPT_TOPIC_ORDER_BY_NAME = "\"Y\" if you wish to order by name: ";
    private static final String PROMPT_TOPIC_ORDER_BY_PRICE = "\"Y\" if you wish to order by price: ";
    private static final String PROMPT_TOPIC_REVERSE_ORDERING_DIRECTION = "\"Y\" if you wish to sort in descending order: ";
    private static final String YES = "y";

    @Autowired
    private UserCommunication userCommunication;


    public List<OrderingRule> getOrderingRules() {
        List<OrderingRule> orderingRules = new ArrayList<>();
        getOrderingRule(PROMPT_TOPIC_ORDER_BY_NAME, Item_.NAME).ifPresent(orderingRules::add);
        getOrderingRule(PROMPT_TOPIC_ORDER_BY_PRICE, Item_.PRICE).ifPresent(orderingRules::add);
        return orderingRules;
    }

    private Optional<OrderingRule> getOrderingRule(String promptOrderBy, String orderBy) {
        return (userCommunication.requestInput(promptOrderBy)
                .strip().equalsIgnoreCase(YES))
                ? getOrderingRuleWithDirection(orderBy)
                : Optional.empty();
    }

    private Optional<OrderingRule> getOrderingRuleWithDirection(String orderBy) {
        return (userCommunication.requestInput(PROMPT_TOPIC_REVERSE_ORDERING_DIRECTION)
                .strip().equalsIgnoreCase(YES))
                ? Optional.of(new OrderingRule(orderBy, false))
                : Optional.of(new OrderingRule(orderBy, true));
    }

}
