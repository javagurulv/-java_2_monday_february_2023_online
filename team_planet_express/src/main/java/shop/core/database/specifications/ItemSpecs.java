package shop.core.database.specifications;

import jakarta.persistence.criteria.Order;
import jakarta.persistence.criteria.Predicate;
import org.springframework.data.jpa.domain.Specification;
import shop.core.domain.item.Item;
import shop.core.domain.item.Item_;
import shop.core.support.ordering.OrderingRule;

import java.util.ArrayList;
import java.util.List;

public interface ItemSpecs {
    static Specification<Item> findBy(String fieldName, Object y) {
        return (root, query, builder) -> builder.equal(root.get(fieldName), y);
    }

    static Specification<Item> orderBy(List<OrderingRule> orderingRules) {
        return (root, query, builder) -> {
            Predicate predicate = builder.and();
            List<Order> orders = new ArrayList<>();
            for (OrderingRule orderingRule : orderingRules) {
                if (orderingRule.getIsAscending()) {
                    orders.add(builder.asc(root.get(orderingRule.getOrderBy().getDefaultName())));
                } else {
                    orders.add(builder.desc(root.get(orderingRule.getOrderBy().getDefaultName())));
                }

            }
            query.orderBy(orders);
            return predicate;
        };
    }

    static Specification<Item> searchByName(String itemName) {
        return (root, query, builder) -> builder.like(builder.lower(root.get(Item_.name)), "%" + itemName.toLowerCase() + "%");
    }

    static Specification<Item> searchByPrice(Integer min, Integer max) {
        return (root, query, builder) -> {
            Predicate predicate = builder.and();
            if (max != null) {
                predicate = builder.lt(root.get(Item_.price), max);
            }
            if (min != null) {
                predicate = builder.and(predicate, builder.gt(root.get(Item_.price), min));
            }
            return predicate;
        };
    }

}
