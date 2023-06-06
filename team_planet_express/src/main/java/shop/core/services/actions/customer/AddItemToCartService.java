package shop.core.services.actions.customer;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;
import shop.core.database.CartItemRepository;
import shop.core.database.CartRepository;
import shop.core.database.ItemRepository;
import shop.core.domain.cart.Cart;
import shop.core.domain.cart_item.CartItem;
import shop.core.domain.item.Item;
import shop.core.domain.user.User;
import shop.core.requests.customer.AddItemToCartRequest;
import shop.core.responses.CoreError;
import shop.core.responses.customer.AddItemToCartResponse;
import shop.core.services.actions.shared.SecurityService;
import shop.core.services.validators.actions.customer.AddItemToCartValidator;

import java.util.List;
import java.util.Optional;

@Component
@Transactional
public class AddItemToCartService {

    @Autowired
    private ItemRepository itemRepository;
    @Autowired
    private CartItemRepository cartItemRepository;
    @Autowired
    private CartRepository cartRepository;
    @Autowired
    private AddItemToCartValidator validator;
    @Autowired
    private SecurityService securityService;

    public AddItemToCartResponse execute(AddItemToCartRequest request) {
        List<CoreError> errors = validator.validate(request);
        if (!errors.isEmpty()) {
            return new AddItemToCartResponse(errors);
        }
        Optional<User> user = securityService.getAuthenticatedUserFromDB();
        if(user.isPresent()) {
            Cart cart = cartRepository.findOpenCartForUserId(user.get().getId()).get();
            Item item = itemRepository.findByName(request.getItemName()).get();
            Integer orderedQuantity = Integer.parseInt(request.getOrderedQuantity());
            addItemToCart(cart, item, orderedQuantity);
            changeItemAvailability(item, orderedQuantity);
        }
        return new AddItemToCartResponse();
    }

    private void addItemToCart(Cart cart, Item item, Integer orderedQuantity) {
        Optional<CartItem> cartItem = cartItemRepository.findByCartIdAndItemId(cart.getId(), item.getId());
        if (cartItem.isEmpty()) {
            cartItemRepository.save(new CartItem(cart, item, orderedQuantity));
        } else {
            Integer newCartItemQuantity = cartItem.get().getOrderedQuantity() + orderedQuantity;
            cartItemRepository.changeOrderedQuantity(cartItem.get().getId(), newCartItemQuantity);
        }
    }

    private void changeItemAvailability(Item item, Integer orderedQuantity) {
        Integer newAvailableQuantity = item.getAvailableQuantity() - orderedQuantity;
        itemRepository.changeAvailableQuantity(item.getId(), newAvailableQuantity);
    }

}
