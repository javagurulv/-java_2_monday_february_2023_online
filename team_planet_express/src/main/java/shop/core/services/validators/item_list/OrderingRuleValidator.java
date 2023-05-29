package shop.core.services.validators.item_list;

import org.springframework.stereotype.Component;
import shop.core.responses.CoreError;
import shop.core.services.exception.InternalSystemCollapseException;
import shop.core.support.ordering.OrderingRule;

import java.util.ArrayList;
import java.util.List;

@Component
public class OrderingRuleValidator {

    public List<CoreError> validate(OrderingRule orderingRule) {
        List<CoreError> errors = new ArrayList<>();
        validateOrderBy(orderingRule.getOrderBy());
        validateOrderingDirection(orderingRule.isAscending());
        return errors;
    }

    private void validateOrderBy(String orderBy) {
        if (orderBy == null) {
            throw new InternalSystemCollapseException();
        }
    }

    private void validateOrderingDirection(Boolean orderDirection) {
        if (orderDirection == null) {
            throw new InternalSystemCollapseException();
        }
    }

}
