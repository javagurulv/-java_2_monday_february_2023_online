package shop.core.services.validators.actions.customer;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import shop.core.database.Repository;
import shop.core.domain.cart.Cart;
import shop.core.domain.item.Item;
import shop.core.domain.user.User;
import shop.core.requests.customer.RemoveItemFromCartRequest;
import shop.core.responses.CoreError;
import shop.core.services.validators.cart.CartValidator;
import shop.core.services.validators.universal.system.CurrentUserIdValidator;
import shop.core.services.validators.universal.system.DatabaseAccessValidator;
import shop.core.services.validators.universal.user_input.InputStringValidator;
import shop.core.services.validators.universal.user_input.InputStringValidatorData;
import shop.core.support.error_code_processing.ErrorProcessor;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Component
public class RemoveItemFromCartValidator {

    private static final String FIELD_NAME = "name";
    private static final String VALUE_NAME_ITEM = "Item name";
    private static final String ERROR_NO_SUCH_ITEM_IN_CART = "VDT-RIC-NIC";
    private static final String ERROR_NO_SUCH_ITEM_IN_SHOP = "VDT-RIC-NIS";

    @Autowired
    private Repository repository;
    @Autowired
    private CurrentUserIdValidator userIdValidator;
    @Autowired
    private CartValidator cartValidator;
    @Autowired
    private InputStringValidator inputStringValidator;
    @Autowired
    private DatabaseAccessValidator databaseAccessValidator;
    @Autowired
    private ErrorProcessor errorProcessor;

    public List<CoreError> validate(RemoveItemFromCartRequest request) {
        userIdValidator.validateCurrentUserIdIsPresent(request.getUserId());
        List<CoreError> errors = new ArrayList<>();
        cartValidator.validateOpenCartExistsForUserId(request.getUserId().getUser()).ifPresent(errors::add);
        if (errors.isEmpty()) {
            validateItemName(request.getItemName(), errors);
            if (errors.isEmpty()) {
                validateItemNameInCart(request).ifPresent(errors::add);
            }
        }
        return errors;
    }

    private void validateItemName(String itemName, List<CoreError> errors) {
        InputStringValidatorData inputStringValidatorData =
                new InputStringValidatorData(itemName, FIELD_NAME, VALUE_NAME_ITEM);
        inputStringValidator.validateIsPresent(inputStringValidatorData).ifPresent(errors::add);
        validateItemNameInShop(itemName).ifPresent(errors::add);
    }

    private Optional<CoreError> validateItemNameInShop(String itemName) {
        return (repository.accessItemDatabase().findByName(itemName).isEmpty())
                ? Optional.of(errorProcessor.getCoreError(FIELD_NAME, ERROR_NO_SUCH_ITEM_IN_SHOP))
                : Optional.empty();
    }

    private Optional<CoreError> validateItemNameInCart(RemoveItemFromCartRequest request) {
        Cart cart = databaseAccessValidator.getOpenCartByUserId(request.getUserId().getUser());
        Item item = databaseAccessValidator.getItemByName(request.getItemName());
        return (repository.accessCartItemDatabase().findByCartIdAndItemId(cart, item).isEmpty())
                ? Optional.of(errorProcessor.getCoreError(FIELD_NAME, ERROR_NO_SUCH_ITEM_IN_CART))
                : Optional.empty();
    }

}
