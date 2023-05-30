package shop.core.services.validators.universal.system;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import shop.core.database.CartItemRepository;
import shop.core.database.CartRepository;
import shop.core.database.ItemRepository;
import shop.core.database.UserRepository;
import shop.core.domain.cart.Cart;
import shop.core.domain.cart_item.CartItem;
import shop.core.domain.item.Item;
import shop.core.domain.user.User;
import shop.core.services.exception.ServiceMissingDataException;

@Component
public class DatabaseAccessValidator {

    @Autowired
    private UserRepository userRepository;
    @Autowired
    private ItemRepository itemRepository;
    @Autowired
    private CartRepository cartRepository;
    @Autowired
    private CartItemRepository cartItemRepository;

    public User getUserById(Long userId) {
        return userRepository.findById(userId)
                .orElseThrow(ServiceMissingDataException::new);
    }

    public User getUserByLoginName(String login) {
        return userRepository.findByLoginName(login)
                .orElseThrow(ServiceMissingDataException::new);
    }

    public Item getItemById(Long itemId) {
        return itemRepository.findById(itemId)
                .orElseThrow(ServiceMissingDataException::new);
    }

    public Item getItemByName(String itemName) {
        return itemRepository.findByName(itemName)
                .orElseThrow(ServiceMissingDataException::new);
    }

    public Cart getOpenCartByUserId(Long userId) {
        return cartRepository.findOpenCartForUserId(userId)
                .orElseThrow(ServiceMissingDataException::new);
    }

    public CartItem getCartItemByCartIdAndItemId(Long cartId, Long itemId) {
        return cartItemRepository.findByCartIdAndItemId(cartId, itemId)
                .orElseThrow(ServiceMissingDataException::new);
    }

}
