package shop.core.services.validators.actions.customer;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import shop.core.database.jpa.JpaCartItemRepository;
import shop.core.database.jpa.JpaItemRepository;
import shop.core.domain.Cart;
import shop.core.domain.Item;
import shop.core.error_code_processing.ErrorProcessor;
import shop.core.requests.customer.RemoveItemFromCartRequest;
import shop.core.responses.CoreError;
import shop.core.services.validators.cart.CartValidator;
import shop.core.services.validators.universal.system.CurrentUserIdValidator;
import shop.core.services.validators.universal.system.RepositoryAccessValidator;
import shop.core.services.validators.universal.user_input.InputStringValidator;
import shop.core.services.validators.universal.user_input.InputStringValidatorData;

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
    private JpaItemRepository itemRepository;
    @Autowired
    private JpaCartItemRepository cartItemRepository;
    @Autowired
    private CurrentUserIdValidator userIdValidator;
    @Autowired
    private CartValidator cartValidator;
    @Autowired
    private InputStringValidator inputStringValidator;
    @Autowired
    private RepositoryAccessValidator repositoryAccessValidator;
    @Autowired
    private ErrorProcessor errorProcessor;

    public List<CoreError> validate(RemoveItemFromCartRequest request) {
        userIdValidator.validateCurrentUserIdIsPresent(request.getCurrentUserId());
        List<CoreError> errors = new ArrayList<>();
        cartValidator.validateOpenCartExistsForUser(
                repositoryAccessValidator.getUserById(request.getCurrentUserId().getValue())).ifPresent(errors::add);
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
        if (errors.isEmpty()) {
            validateItemNameInShop(itemName).ifPresent(errors::add);
        }
    }

    private Optional<CoreError> validateItemNameInShop(String itemName) {
        return (itemRepository.findByName(itemName).isEmpty())
                ? Optional.of(errorProcessor.getCoreError(FIELD_NAME, ERROR_NO_SUCH_ITEM_IN_SHOP))
                : Optional.empty();
    }

    private Optional<CoreError> validateItemNameInCart(RemoveItemFromCartRequest request) {
        Cart cart = repositoryAccessValidator.getOpenCartByUser(
                repositoryAccessValidator.getUserById(request.getCurrentUserId().getValue()));
        Item item = repositoryAccessValidator.getItemByName(request.getItemName());
        return (cartItemRepository.findFirstByCartAndItem(cart, item).isEmpty())
                ? Optional.of(errorProcessor.getCoreError(FIELD_NAME, ERROR_NO_SUCH_ITEM_IN_CART))
                : Optional.empty();
    }

}
