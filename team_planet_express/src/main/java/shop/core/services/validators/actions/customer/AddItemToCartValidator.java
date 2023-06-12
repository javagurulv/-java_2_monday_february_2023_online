package shop.core.services.validators.actions.customer;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import shop.core.database.ItemRepository;
import shop.core.domain.user.User;
import shop.core.services.actions.shared.SecurityServiceImpl;
import shop.core.services.validators.cart.CartValidator;
import shop.core.services.validators.universal.system.DatabaseAccessProvider;
import shop.core.services.validators.universal.user_input.InputStringValidator;
import shop.core.services.validators.universal.user_input.InputStringValidatorData;
import shop.core.support.error_code_processing.ErrorProcessor;
import shop.core_api.requests.customer.AddItemToCartRequest;
import shop.core_api.responses.CoreError;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Component
public class AddItemToCartValidator {

    private static final String FIELD_NAME = "name";
    private static final String FIELD_QUANTITY = "quantity";
    private static final String VALUE_NAME_ITEM = "Item name";
    private static final String VALUE_NAME_QUANTITY = "Quantity";
    private static final String ERROR_NO_SUCH_ITEM = "VDT-AIC-NIS";
    private static final String ERROR_NOT_ENOUGH_QUANTITY = "VDT-AIC-NEQ";

    @Autowired
    private ItemRepository itemRepository;
    @Autowired
    private CartValidator cartValidator;
    @Autowired
    private InputStringValidator inputStringValidator;
    @Autowired
    private DatabaseAccessProvider databaseAccessProvider;
    @Autowired
    private ErrorProcessor errorProcessor;
    @Autowired
    private SecurityServiceImpl securityService;

    public List<CoreError> validate(AddItemToCartRequest request) {
        List<CoreError> errors = new ArrayList<>();
        Optional<User> user = securityService.getAuthenticatedUserFromDB();
        if(user.isEmpty())
            errors.add(new CoreError("","", ""));
        else
            cartValidator.validateOpenCartExistsForUserId(user.get().getId()).ifPresent(errors::add);
        if (errors.isEmpty()) {
            validateItemName(request.getItemName(), errors);
            validateQuantity(request.getOrderedQuantity(), errors);
            if (errors.isEmpty()) {
                validateOrderedQuantityNotGreaterThanAvailable(request).ifPresent(errors::add);
            }
        }
        return errors;
    }

    private void validateItemName(String itemName, List<CoreError> errors) {
        InputStringValidatorData inputStringValidatorData =
                new InputStringValidatorData(itemName, FIELD_NAME, VALUE_NAME_ITEM);
        inputStringValidator.validateIsPresent(inputStringValidatorData).ifPresent(errors::add);
        validateItemNameExistsInShop(itemName).ifPresent(errors::add);
    }

    private void validateQuantity(String orderedQuantity, List<CoreError> errors) {
        InputStringValidatorData inputStringValidatorData =
                new InputStringValidatorData(orderedQuantity, FIELD_QUANTITY, VALUE_NAME_QUANTITY);
        inputStringValidator.validateIsPresent(inputStringValidatorData).ifPresent(errors::add);
        errors.addAll(inputStringValidator.validateIsNumberGreaterThanZeroNotDecimal(inputStringValidatorData));
    }

    private Optional<CoreError> validateOrderedQuantityNotGreaterThanAvailable(AddItemToCartRequest request) {
        return (Integer.parseInt(request.getOrderedQuantity()) >
                databaseAccessProvider.getItemByName(request.getItemName()).getAvailableQuantity())
                ? Optional.of(errorProcessor.getCoreError(FIELD_QUANTITY, ERROR_NOT_ENOUGH_QUANTITY))
                : Optional.empty();
    }

    private Optional<CoreError> validateItemNameExistsInShop(String itemName) {
        return (itemName == null ||
                itemRepository.findByName(itemName).isEmpty())
                ? Optional.of(errorProcessor.getCoreError(FIELD_NAME, ERROR_NO_SUCH_ITEM))
                : Optional.empty();
    }

}
