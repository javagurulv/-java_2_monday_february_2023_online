package shop.core.services.item_list;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;
import shop.core.domain.item.Item;
import shop.core.support.ordering.OrderBy;
import shop.core.support.ordering.OrderDirection;
import shop.core.support.ordering.OrderingRule;

import java.util.Arrays;
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

    public String getSQLOrderBy(List<OrderingRule> orderingRules) {
        String orderBy = "";
        if (orderingEnabled) {
            if (orderingRules != null && orderingRules.size() > 0) {
                List<String> sqlParts = getSQLParts(orderingRules);
                orderBy = concatenateSQLParts(sqlParts);
            }
        }
        return orderBy;
    }

    private List<Item> orderByPrice(List<Item> items, List<OrderingRule> orderingRules) {
        Optional<OrderingRule> orderingRuleForPrice = getOrderingRule(orderingRules, OrderBy.PRICE);
        return orderWithDirection(items, orderingRuleForPrice, Comparator.comparing(Item::getPrice));
    }

    private List<Item> orderByName(List<Item> items, List<OrderingRule> orderingRules) {
        Optional<OrderingRule> orderingRuleForName = getOrderingRule(orderingRules, OrderBy.NAME);
        return orderWithDirection(items, orderingRuleForName, Comparator.comparing(Item::getName, String.CASE_INSENSITIVE_ORDER));
    }

    private List<String> getSQLParts(List<OrderingRule> orderingRules) {
        return Arrays.stream(OrderBy.values())
                .map(orderByValue -> getOrderingRule(orderingRules, orderByValue))
                .flatMap(Optional::stream)
                .map(this::getOrderByPart)
                .toList();
    }

    private String concatenateSQLParts(List<String> sqlParts) {
        if (sqlParts.size() > 0) {
            return "ORDER BY " + String.join(", ", sqlParts);
        }
        return "";
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

    private String getOrderByPart(OrderingRule orderingRule) {
        return orderingRule.getOrderBy().toString().toLowerCase() + " " + orderingRule.getOrderDirection().getText();
    }

}
