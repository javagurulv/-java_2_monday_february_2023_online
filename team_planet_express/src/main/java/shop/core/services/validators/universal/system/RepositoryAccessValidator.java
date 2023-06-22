package shop.core.services.validators.universal.system;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import shop.core.database.jpa.JpaCartItemRepository;
import shop.core.database.jpa.JpaCartRepository;
import shop.core.database.jpa.JpaItemRepository;
import shop.core.database.jpa.JpaUserRepository;
import shop.core.domain.Cart;
import shop.core.domain.CartItem;
import shop.core.domain.Item;
import shop.core.domain.User;
import shop.core.services.exception.ServiceMissingDataException;

@Component
public class RepositoryAccessValidator {

    @Autowired
    private JpaUserRepository userRepository;
    @Autowired
    private JpaItemRepository itemRepository;
    @Autowired
    private JpaCartRepository cartRepository;
    @Autowired
    private JpaCartItemRepository cartItemRepository;

    public User getUserById(Long userId) {
        return userRepository.findById(userId)
                .orElseThrow(ServiceMissingDataException::new);
    }

    public User getUserByLoginName(String login) {
        return userRepository.findFirstByLogin(login)
                .orElseThrow(ServiceMissingDataException::new);
    }

    public Item getItemById(Long itemId) {
        return itemRepository.findById(itemId)
                .orElseThrow(ServiceMissingDataException::new);
    }

    public Item getItemByName(String itemName) {
        return itemRepository.findFirstByName(itemName)
                .orElseThrow(ServiceMissingDataException::new);
    }

    public Cart getOpenCartByUser(User user) {
        return cartRepository.findOpenCartByUser(user)
                .orElseThrow(ServiceMissingDataException::new);
    }

    public CartItem getCartItemByCartAndItem(Cart cart, Item item) {
        return cartItemRepository.findFirstByCartAndItem(cart, item)
                .orElseThrow(ServiceMissingDataException::new);
    }

}
