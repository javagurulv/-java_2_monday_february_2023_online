package shop.core.services.actions.customer;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;
import shop.core.database.CartItemRepository;
import shop.core.database.ItemRepository;
import shop.core.domain.cart.Cart;
import shop.core.domain.cart_item.CartItem;
import shop.core.domain.item.Item;
import shop.core.domain.user.User;
import shop.core.requests.customer.RemoveItemFromCartRequest;
import shop.core.responses.CoreError;
import shop.core.responses.customer.RemoveItemFromCartResponse;
import shop.core.services.actions.shared.SecurityService;
import shop.core.services.validators.actions.customer.RemoveItemFromCartValidator;
import shop.core.services.validators.universal.system.DatabaseAccessValidator;

import java.util.List;
import java.util.Optional;

@Component
@Transactional
public class RemoveItemFromCartService {

    @Autowired
    private ItemRepository itemRepository;
    @Autowired
    private CartItemRepository cartItemRepository;
    @Autowired
    private RemoveItemFromCartValidator validator;
    @Autowired
    private DatabaseAccessValidator databaseAccessValidator;
    @Autowired
    private SecurityService securityService;


    public RemoveItemFromCartResponse execute(RemoveItemFromCartRequest request) {
        List<CoreError> errors = validator.validate(request);
        if (!errors.isEmpty()) {
            return new RemoveItemFromCartResponse(errors);
        }
        Optional<User> user = securityService.getAuthenticatedUserFromDB();
        Cart cart = databaseAccessValidator.getOpenCartByUserId(user.get().getId());
        Item item = databaseAccessValidator.getItemByName(request.getItemName());
        CartItem cartItem = databaseAccessValidator.getCartItemByCartIdAndItemId(cart.getId(), item.getId());
        Integer newAvailableQuantity = item.getAvailableQuantity() + cartItem.getOrderedQuantity();
        cartItemRepository.deleteByID(cartItem.getId());
        itemRepository.changeAvailableQuantity(item.getId(), newAvailableQuantity);
        return new RemoveItemFromCartResponse();
    }

}
