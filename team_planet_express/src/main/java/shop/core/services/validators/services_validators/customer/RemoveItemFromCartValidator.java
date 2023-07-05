package shop.core.services.validators.services_validators.customer;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import shop.core.database.CartItemRepository;
import shop.core.database.CartRepository;
import shop.core.database.ItemRepository;
import shop.core.database.specifications.CartSpecs;
import shop.core.domain.cart.Cart;
import shop.core.domain.user.User;
import shop.core.services.actions.shared.SecurityServiceImpl;
import shop.core.services.validators.cart.CartValidator;
import shop.core.services.validators.error_code_processing.ErrorProcessor;
import shop.core.services.validators.universal.user_input.InputStringValidator;
import shop.core_api.requests.customer.RemoveItemFromCartRequest;
import shop.core_api.responses.CoreError;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Component
public class RemoveItemFromCartValidator {

    private static final String FIELD_NAME = "name";
    private static final String VALUE_NAME_ITEM = "Item name";
    private static final String ERROR_NO_SUCH_ITEM_IN_CART = "VDT-RIC-NIC";
    private static final String ERROR_NO_SUCH_ITEM_IN_SHOP = "VDT-RIC-NIS";
    private static final String ERROR_NO_OPEN_CART = "VDT-CRT-NOC";

    @Autowired
    private ItemRepository itemRepository;
    @Autowired
    private CartItemRepository cartItemRepository;
    @Autowired
    private CartRepository cartRepository;
    @Autowired
    private CartValidator cartValidator;
    @Autowired
    private InputStringValidator inputStringValidator;
    @Autowired
    private ErrorProcessor errorProcessor;
    @Autowired
    private SecurityServiceImpl securityService;

    public List<CoreError> validate(RemoveItemFromCartRequest request) {
        List<CoreError> errors = new ArrayList<>();
        Optional<User> user = securityService.getAuthenticatedUserFromDB();
        if(user.isEmpty())
            errors.add(new CoreError("","", ""));

        if (errors.isEmpty()) {
            Optional<Cart> cart = cartRepository.findOne(CartSpecs.findOpenCartForUser(user.get()));
            if (cart.isEmpty()) {
                errorProcessor.getCoreError(FIELD_NAME, ERROR_NO_OPEN_CART);
            } else {
                if (cart.get().getUser().getId() != user.get().getId())
                    errorProcessor.getCoreError(FIELD_NAME, ERROR_NO_OPEN_CART);
            }
            if (errors.isEmpty()) {
                if (cartItemRepository.existsById(request.getCartItemDTO().getId())) {
                    errorProcessor.getCoreError(FIELD_NAME, ERROR_NO_SUCH_ITEM_IN_CART);
                }
            }
        }
        return errors;
    }

}
