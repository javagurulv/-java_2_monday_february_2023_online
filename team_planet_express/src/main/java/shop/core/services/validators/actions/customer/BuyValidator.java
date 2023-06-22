package shop.core.services.validators.actions.customer;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import shop.core.database.jpa.JpaCartItemRepository;
import shop.core.domain.Cart;
import shop.core.domain.User;
import shop.core.error_code_processing.ErrorProcessor;
import shop.core.requests.customer.BuyRequest;
import shop.core.responses.CoreError;
import shop.core.services.validators.cart.CartValidator;
import shop.core.services.validators.universal.system.CurrentUserIdValidator;
import shop.core.services.validators.universal.system.RepositoryAccessValidator;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Component
public class BuyValidator {

    private static final String FIELD_NAME = "name";
    private static final String ERROR_CART_EMPTY = "VDT-BUY-CIE";

    @Autowired
    private JpaCartItemRepository cartItemRepository;
    @Autowired
    private CurrentUserIdValidator userIdValidator;
    @Autowired
    private CartValidator cartValidator;
    @Autowired
    private RepositoryAccessValidator repositoryAccessValidator;
    @Autowired
    private ErrorProcessor errorProcessor;

    public List<CoreError> validate(BuyRequest request) {
        userIdValidator.validateCurrentUserIdIsPresent(request.getCurrentUserId());
        List<CoreError> errors = new ArrayList<>();
        cartValidator.validateOpenCartExistsForUser(
                repositoryAccessValidator.getUserById(request.getCurrentUserId().getValue())).ifPresent(errors::add);
        if (errors.isEmpty()) {
            validateCartIsNotEmpty(
                    repositoryAccessValidator.getUserById(request.getCurrentUserId().getValue())).ifPresent(errors::add);
        }
        return errors;
    }

    private Optional<CoreError> validateCartIsNotEmpty(User user) {
        Cart cart = repositoryAccessValidator.getOpenCartByUser(user);
        return (cartItemRepository.findByCart(cart).size() == 0)
                ? Optional.of(errorProcessor.getCoreError(FIELD_NAME, ERROR_CART_EMPTY))
                : Optional.empty();
    }

}
