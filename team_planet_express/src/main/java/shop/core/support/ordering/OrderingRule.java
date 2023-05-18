package shop.core.support.ordering;

public class OrderingRule {

    private final OrderBy orderBy;
    private final OrderDirection orderDirection;

    public OrderingRule(OrderBy orderBy, OrderDirection orderDirection) {
        this.orderBy = orderBy;
        this.orderDirection = orderDirection;
    }

    public OrderBy getOrderBy() {
        return orderBy;
    }

    public OrderDirection getOrderDirection() {
        return orderDirection;
    }

}
