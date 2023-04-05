package Shop.core.services.item.ordering;

import Shop.core.domain.item.Item;
import Shop.core.support.ordering.OrderBy;
import Shop.core.support.ordering.OrderDirection;
import Shop.core.support.ordering.OrderingRule;

import java.util.Comparator;
import java.util.List;
import java.util.Optional;

public class OrderingService {

    public List<Item> getOrderedItems(List<Item> items, List<OrderingRule> orderingRules) {
        if (orderingRules != null && orderingRules.size() > 0) {
            items = orderByPrice(items, orderingRules);
            items = orderByName(items, orderingRules);
        }
        return items;
    }

    private List<Item> orderByPrice(List<Item> items, List<OrderingRule> orderingRules) {
        Optional<OrderingRule> orderingRuleForPrice = getOrderingRule(orderingRules, OrderBy.PRICE);
        return orderWithDirection(items, orderingRuleForPrice, Comparator.comparing(Item::getPrice));
    }

    private List<Item> orderByName(List<Item> items, List<OrderingRule> orderingRules) {
        Optional<OrderingRule> orderingRuleForName = getOrderingRule(orderingRules, OrderBy.NAME);
        return orderWithDirection(items, orderingRuleForName, Comparator.comparing(Item::getName));
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