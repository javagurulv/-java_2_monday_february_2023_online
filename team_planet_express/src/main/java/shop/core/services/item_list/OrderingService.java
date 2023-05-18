package shop.core.services.item_list;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;
import shop.core.domain.item.Item;
import shop.core.support.ordering.OrderBy;
import shop.core.support.ordering.OrderDirection;
import shop.core.support.ordering.OrderingRule;

import java.util.Comparator;
import java.util.List;
import java.util.Optional;

@Component
public class OrderingService {

    @Value("${ordering.enabled}")
    private boolean orderingEnabled;

    public List<Item> getOrderedItems(List<Item> items, List<OrderingRule> orderingRules) {
        if (orderingEnabled) {
            if (orderingRules != null && orderingRules.size() > 0) {
                items = orderByPrice(items, orderingRules);
                items = orderByName(items, orderingRules);
            }
        }
        return items;
    }

    //TODO this is trash
    public String getSQLOrderBy(List<OrderingRule> orderingRules) {
        StringBuilder orderBy = new StringBuilder();
        if (orderingEnabled) {
            if (orderingRules != null && orderingRules.size() > 0) {
                String orderByPrice = "";
                String orderByName = "";
                Optional<OrderingRule> orderingRuleForPrice = getOrderingRule(orderingRules, OrderBy.PRICE);
                if (orderingRuleForPrice.isPresent()) {
                    orderByPrice = orderingRuleForPrice.get().getOrderBy().toString().toLowerCase() +
                            " " +
                            orderingRuleForPrice.get().getOrderDirection().getText();
                }
                Optional<OrderingRule> orderingRuleForName = getOrderingRule(orderingRules, OrderBy.NAME);
                if (orderingRuleForName.isPresent()) {
                    orderByName = orderingRuleForName.get().getOrderBy().toString().toLowerCase() +
                            " " +
                            orderingRuleForName.get().getOrderDirection().getText();
                }
                if (orderByName.length() > 0) {
                    orderBy.append("ORDER BY ").append(orderByName);
                }
                if (orderByPrice.length() > 0) {
                    if (orderBy.length() > 0) {
                        orderBy.append(", ");
                    } else {
                        orderBy.append("ORDER BY ");
                    }
                    orderBy.append(orderByPrice);
                }
            }
        }
        return orderBy.toString();
    }

    private List<Item> orderByPrice(List<Item> items, List<OrderingRule> orderingRules) {
        Optional<OrderingRule> orderingRuleForPrice = getOrderingRule(orderingRules, OrderBy.PRICE);
        return orderWithDirection(items, orderingRuleForPrice, Comparator.comparing(Item::getPrice));
    }

    private List<Item> orderByName(List<Item> items, List<OrderingRule> orderingRules) {
        Optional<OrderingRule> orderingRuleForName = getOrderingRule(orderingRules, OrderBy.NAME);
        return orderWithDirection(items, orderingRuleForName, Comparator.comparing(Item::getName, String.CASE_INSENSITIVE_ORDER));
    }

    private Optional<OrderingRule> getOrderingRule(List<OrderingRule> orderingRules, OrderBy orderBy) {
        return orderingRules.stream()
                .filter(orderingRule -> orderBy.equals(orderingRule.getOrderBy()))
                .findFirst();
    }

    private List<Item> orderWithDirection(List<Item> items, Optional<OrderingRule> orderingRule, Comparator<Item> comparator) {
        return orderingRule.map(rule -> items.stream()
                .sorted(rule.getOrderDirection().equals(OrderDirection.DESCENDING)
                        ? comparator.reversed()
                        : comparator)
                .toList()).orElse(items);
    }

}
