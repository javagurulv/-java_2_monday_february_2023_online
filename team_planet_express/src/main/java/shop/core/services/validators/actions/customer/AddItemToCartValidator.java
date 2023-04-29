package shop.core.services.validators.actions.customer;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import shop.core.database.Database;
import shop.core.requests.customer.AddItemToCartRequest;
import shop.core.responses.CoreError;
import shop.core.services.validators.cart.CartValidator;
import shop.core.services.validators.universal.system.CurrentUserIdValidator;
import shop.core.services.validators.universal.system.DatabaseAccessValidator;
import shop.core.services.validators.universal.user_input.InputStringValidator;
import shop.core.services.validators.universal.user_input.InputStringValidatorData;
import shop.core.support.ErrorCodeUtil;
import shop.core.support.Placeholder;

import java.util.ArrayList;
import java.util.List;

@Component
public class AddItemToCartValidator {

    @Autowired
    private ErrorCodeUtil errorCodeUtil;

    @Autowired
    private Database database;
    @Autowired
    private CurrentUserIdValidator userIdValidator;
    @Autowired
    private CartValidator cartValidator;
    @Autowired
    private InputStringValidator inputStringValidator;
    @Autowired
    private DatabaseAccessValidator databaseAccessValidator;


    public List<CoreError> validate(AddItemToCartRequest request) {
        userIdValidator.validateCurrentUserIdIsPresent(request.getUserId());
        List<CoreError> errors = new ArrayList<>();
        cartValidator.validateOpenCartExistsForUserId(request.getUserId().getValue()).ifPresent(errors::add);
        if (errors.isEmpty()) {
            validateItemName(request.getItemName(), errors);
            validateQuantity(request.getOrderedQuantity(), errors);
        }
        if (errors.isEmpty()) {
            validateOrderedQuantityNotGreaterThanAvailable(request, errors);
        }
        return errors;
    }

    private void validateItemName(String itemName, List<CoreError> errors) {
        List<Placeholder> placeholders = new ArrayList<>();
        placeholders.add(new Placeholder("VALUE", "Item name"));

        InputStringValidatorData inputStringValidatorData =
                new InputStringValidatorData(itemName, placeholders);

        inputStringValidatorData.setPresentChecker(true);
        errors.addAll(inputStringValidator.validate(inputStringValidatorData));

        validateItemNameExistsInShop(itemName, errors);
    }

    private void validateQuantity(String orderedQuantity, List<CoreError> errors) {
        List<Placeholder> placeholders = new ArrayList<>();
        placeholders.add(new Placeholder("VALUE", "Quantity"));

        InputStringValidatorData inputStringValidatorData =
                new InputStringValidatorData(orderedQuantity, placeholders);

        inputStringValidatorData.setPresentChecker(true);
        inputStringValidatorData.setNumberChecker(true);
        inputStringValidatorData.setGreaterZeroChecker(true);
        inputStringValidatorData.setNotDecimalChecker(true);

        errors.addAll(inputStringValidator.validate(inputStringValidatorData));
    }

    private void validateOrderedQuantityNotGreaterThanAvailable(AddItemToCartRequest request, List<CoreError> errors) {
        if (Integer.parseInt(request.getOrderedQuantity()) >
                databaseAccessValidator.getItemByName(request.getItemName()).getAvailableQuantity()) {
            errors.add(errorCodeUtil.errorBuild("ERROR_CODE_2"));
        }
    }

    private void validateItemNameExistsInShop(String itemName, List<CoreError> errors) {
        if (itemName == null || database.accessItemDatabase().findByName(itemName).isEmpty()) {
            List<Placeholder> placeholders = new ArrayList<>();
            placeholders.add(new Placeholder("NOT_CONTAIN_REQUESTED_DATA", "the shop"));
            errors.add(errorCodeUtil.errorBuild("ERROR_CODE_1", placeholders));
        }
    }

}
