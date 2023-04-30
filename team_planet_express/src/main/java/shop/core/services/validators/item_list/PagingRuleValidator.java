package shop.core.services.validators.item_list;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import shop.core.responses.CoreError;
import shop.core.services.exception.InternalSystemCollapseException;
import shop.core.services.validators.universal.user_input.InputStringValidator;
import shop.core.services.validators.universal.user_input.InputStringValidatorData;
import shop.core.support.Placeholder;
import shop.core.support.paging.PagingRule;

import java.util.ArrayList;
import java.util.List;

@Component
public class PagingRuleValidator {

    @Autowired
    private InputStringValidator inputStringValidator;

    public List<CoreError> validate(PagingRule pagingRule) {
        List<CoreError> errors = new ArrayList<>();
        validatePageNumber(pagingRule.getPageNumber());
        validatePageSize(pagingRule.getPageSize(), errors);
        return errors;
    }

    private void validatePageNumber(Integer pageNumber) {
        if (pageNumber == null || pageNumber <= 0) {
            throw new InternalSystemCollapseException();
        }
    }

    private void validatePageSize(String pageSize, List<CoreError> errors) {
        List<Placeholder> placeholders = new ArrayList<>();
        placeholders.add(new Placeholder("VALUE", "Page size"));

        InputStringValidatorData inputStringValidatorData =
                new InputStringValidatorData(pageSize, placeholders);
        inputStringValidatorData.setPresentChecker(true);
        inputStringValidatorData.setNumberChecker(true);
        inputStringValidatorData.setGreaterZeroChecker(true);
        inputStringValidatorData.setNotDecimalChecker(true);

        errors.addAll(inputStringValidator.validate(inputStringValidatorData));
    }

}
