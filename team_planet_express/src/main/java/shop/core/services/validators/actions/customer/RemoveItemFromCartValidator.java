package shop.core.services.validators.actions.customer;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import shop.core.database.Database;
import shop.core.domain.cart.Cart;
import shop.core.domain.item.Item;
import shop.core.requests.customer.RemoveItemFromCartRequest;
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
import java.util.Optional;

@Component
public class RemoveItemFromCartValidator {
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

    public List<CoreError> validate(RemoveItemFromCartRequest request) {
        userIdValidator.validateCurrentUserIdIsPresent(request.getUserId());

        List<CoreError> errors = new ArrayList<>();
        cartValidator.validateOpenCartExistsForUserId(request.getUserId().getValue()).ifPresent(errors::add);
        if (errors.isEmpty()) validateItemName(request.getItemName(), errors);
        if (errors.isEmpty()) validateItemNameInCart(request).ifPresent(errors::add);
        return errors;
    }

    private void validateItemName(String itemName, List<CoreError> errors) {
        List<Placeholder> placeholders = new ArrayList<>();
        placeholders.add(new Placeholder("VALUE", "Item name"));

        InputStringValidatorData inputStringValidatorData =
                new InputStringValidatorData(itemName, placeholders);
        inputStringValidatorData.setPresentChecker(true);
        errors.addAll(inputStringValidator.validate(inputStringValidatorData));
        validateItemNameInShop(itemName).ifPresent(errors::add);
    }

    private Optional<CoreError> validateItemNameInShop(String itemName) {
        return (database.accessItemDatabase().findByName(itemName).isEmpty())
                ? Optional.of(errorCodeUtil.errorBuild(
                "ERROR_CODE_1",
                List.of(new Placeholder("NOT_CONTAIN_REQUESTED_DATA", "the shop")))
        )
                : Optional.empty();
    }

    private Optional<CoreError> validateItemNameInCart(RemoveItemFromCartRequest request) {
        Cart cart = databaseAccessValidator.getOpenCartByUserId(request.getUserId().getValue());
        Item item = databaseAccessValidator.getItemByName(request.getItemName());
        return (database.accessCartItemDatabase().findByCartIdAndItemId(cart.getId(), item.getId()).isEmpty())
                ? Optional.of(errorCodeUtil.errorBuild(
                "ERROR_CODE_1",
                List.of(new Placeholder("NOT_CONTAIN_REQUESTED_DATA", "your cart")))
        )
                : Optional.empty();
    }

}
