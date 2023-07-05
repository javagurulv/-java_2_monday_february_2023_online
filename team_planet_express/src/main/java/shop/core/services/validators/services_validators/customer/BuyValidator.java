package shop.core.services.validators.services_validators.customer;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import shop.core.database.CartItemRepository;
import shop.core.database.CartRepository;
import shop.core.domain.cart.Cart;
import shop.core.domain.cart_item.CartItem;
import shop.core.domain.cart_item.CartItem_;
import shop.core.services.validators.error_code_processing.ErrorProcessor;
import shop.core_api.entry_point.shared.SecurityService;
import shop.core_api.requests.customer.BuyRequest;
import shop.core_api.responses.CoreError;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import static shop.core.database.specifications.CartItemSpecs.findBy;

@Component
public class BuyValidator {

    private static final String FIELD_NAME = "name";
    private static final String ERROR_CART_EMPTY = "VDT-BUY-CIE";
    private static final String ERROR_NO_OPEN_CART = "VDT-CRT-NOC";

    @Autowired
    private CartRepository cartRepository;
    @Autowired
    private CartItemRepository cartItemRepository;
    @Autowired
    private SecurityService securityService;
    @Autowired
    private ErrorProcessor errorProcessor;

    public List<CoreError> validate(BuyRequest request) {
//        userIdValidator.validateCurrentUserIdIsPresent(request.getCurrentUserId());
        List<CoreError> errors = new ArrayList<>();

        if (securityService.getAuthenticatedUserFromDB().isEmpty())
            errors.add(new CoreError("", "", ""));

        if (request.getCartDTO().getId() != null && cartRepository.existsById(request.getCartDTO().getId()))
            errors.add(errorProcessor.getCoreError(FIELD_NAME, ERROR_NO_OPEN_CART));

        if (errors.isEmpty()) {
            Cart cart = new Cart();
            cart.setId(request.getCartDTO().getId());
            List<CartItem> cartItemsToBuy = cartItemRepository.findAll(findBy(CartItem_.CART, cart));
            Optional<CartItem> optionalCartItem = cartItemsToBuy.stream()
                    .filter(cartItem -> cartItem.getOrderedQuantity() >= cartItem.getItem().getAvailableQuantity())
                    .findFirst();
            if (optionalCartItem.isPresent())
                errors.add(new CoreError("", "", ""));
        }
        return errors;
    }

}
