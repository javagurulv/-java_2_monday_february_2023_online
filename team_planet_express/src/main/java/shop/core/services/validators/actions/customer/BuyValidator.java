package shop.core.services.validators.actions.customer;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import shop.core.database.CartItemRepository;
import shop.core.domain.cart.Cart;
import shop.core.services.validators.cart.CartValidator;
import shop.core.services.validators.universal.system.DatabaseAccessProvider;
import shop.core.support.error_code_processing.ErrorProcessor;
import shop.core_api.requests.customer.BuyRequest;
import shop.core_api.responses.CoreError;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Component
public class BuyValidator {

    private static final String FIELD_NAME = "name";
    private static final String ERROR_CART_EMPTY = "VDT-BUY-CIE";

    @Autowired
    private CartItemRepository cartItemRepository;
    @Autowired
    private CartValidator cartValidator;
    @Autowired
    private DatabaseAccessProvider databaseAccessProvider;
    @Autowired
    private ErrorProcessor errorProcessor;

    public List<CoreError> validate(BuyRequest request) {
//        userIdValidator.validateCurrentUserIdIsPresent(request.getCurrentUserId());
        List<CoreError> errors = new ArrayList<>();
//        cartValidator.validateOpenCartExistsForUserId(request.getCurrentUserId().getValue()).ifPresent(errors::add);
//        if (errors.isEmpty()) {
//            validateCartIsNotEmpty(request.getCurrentUserId().getValue()).ifPresent(errors::add);
//        }
        return errors;
    }

    private Optional<CoreError> validateCartIsNotEmpty(Long userId) {
        Cart cart = databaseAccessProvider.getOpenCartByUserId(userId);
        return (cartItemRepository.getAllCartItemsForCartId(cart.getId()).size() == 0)
                ? Optional.of(errorProcessor.getCoreError(FIELD_NAME, ERROR_CART_EMPTY))
                : Optional.empty();
    }

}
