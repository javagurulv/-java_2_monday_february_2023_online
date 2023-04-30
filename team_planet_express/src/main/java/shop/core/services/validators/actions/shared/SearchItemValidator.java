package shop.core.services.validators.actions.shared;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import shop.core.requests.shared.SearchItemRequest;
import shop.core.responses.CoreError;
import shop.core.services.validators.item_list.OrderingRuleValidator;
import shop.core.services.validators.item_list.PagingRuleValidator;
import shop.core.services.validators.universal.system.CurrentUserIdValidator;
import shop.core.services.validators.universal.user_input.InputStringValidator;
import shop.core.services.validators.universal.user_input.InputStringValidatorData;
import shop.core.support.Placeholder;

import java.util.ArrayList;
import java.util.List;

@Component
public class SearchItemValidator {

    @Autowired
    private CurrentUserIdValidator userIdValidator;
    @Autowired
    private InputStringValidator inputStringValidator;
    @Autowired
    private OrderingRuleValidator orderingRuleValidator;
    @Autowired
    private PagingRuleValidator pagingRuleValidator;


    public List<CoreError> validate(SearchItemRequest request) {
        userIdValidator.validateCurrentUserIdIsPresent(request.getUserId());
        List<CoreError> errors = new ArrayList<>();
        validatePrice(request.getPrice(), errors);
        validateOrderingIfPresent(request, errors);
        validatePagingIfPresent(request, errors);
        return errors;
    }

    private void validatePrice(String price, List<CoreError> errors) {
        List<Placeholder> placeholders = new ArrayList<>();
        placeholders.add(new Placeholder("VALUE", "Price"));

        InputStringValidatorData inputStringValidatorData =
                new InputStringValidatorData(price, placeholders);
        inputStringValidatorData.setNumberChecker(true);
        inputStringValidatorData.setNotNegativeChecker(true);

        errors.addAll(inputStringValidator.validate(inputStringValidatorData));
    }

    private void validateOrderingIfPresent(SearchItemRequest request, List<CoreError> errors) {
        if (request.getOrderingRules() != null) {
            request.getOrderingRules().stream()
                    .map(orderingRuleValidator::validate)
                    .filter(errorList -> !errorList.isEmpty())
                    .forEach(errors::addAll);
        }
    }

    private void validatePagingIfPresent(SearchItemRequest request, List<CoreError> errors) {
        if (request.getPagingRule() != null) {
            errors.addAll(pagingRuleValidator.validate(request.getPagingRule()));
        }
    }

}
