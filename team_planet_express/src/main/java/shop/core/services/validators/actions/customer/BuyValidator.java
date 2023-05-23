package shop.core.services.validators.actions.customer;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import shop.core.database.Repository;
import shop.core.domain.cart.Cart;
import shop.core.domain.user.User;
import shop.core.requests.customer.BuyRequest;
import shop.core.responses.CoreError;
import shop.core.services.validators.cart.CartValidator;
import shop.core.services.validators.universal.system.CurrentUserIdValidator;
import shop.core.services.validators.universal.system.DatabaseAccessValidator;
import shop.core.support.error_code_processing.ErrorProcessor;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Component
public class BuyValidator {

    private static final String FIELD_NAME = "name";
    private static final String ERROR_CART_EMPTY = "VDT-BUY-CIE";

    @Autowired
    private Repository repository;
    @Autowired
    private CurrentUserIdValidator userIdValidator;
    @Autowired
    private CartValidator cartValidator;
    @Autowired
    private DatabaseAccessValidator databaseAccessValidator;
    @Autowired
    private ErrorProcessor errorProcessor;

    public List<CoreError> validate(BuyRequest request) {
        userIdValidator.validateCurrentUserIdIsPresent(request.getCurrentUser());
        List<CoreError> errors = new ArrayList<>();
        cartValidator.validateOpenCartExistsForUserId(request.getCurrentUser().getUser()).ifPresent(errors::add);
        if (errors.isEmpty()) {
            validateCartIsNotEmpty(request.getCurrentUser().getUser()).ifPresent(errors::add);
        }
        return errors;
    }

    private Optional<CoreError> validateCartIsNotEmpty(User user) {
        Cart cart = databaseAccessValidator.getOpenCartByUserId(user);
        return (repository.accessCartItemRepository().getAllCartItemsForCartId(cart).size() == 0)
                ? Optional.of(errorProcessor.getCoreError(FIELD_NAME, ERROR_CART_EMPTY))
                : Optional.empty();
    }

}
