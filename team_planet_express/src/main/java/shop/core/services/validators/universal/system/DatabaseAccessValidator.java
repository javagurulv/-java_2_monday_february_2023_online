package shop.core.services.validators.universal.system;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import shop.core.database.Repository;
import shop.core.domain.cart.Cart;
import shop.core.domain.cart_item.CartItem;
import shop.core.domain.item.Item;
import shop.core.domain.user.User;
import shop.core.services.exception.ServiceMissingDataException;

@Component
public class DatabaseAccessValidator {

    @Autowired
    private Repository repository;

    public User getUserById(Long userId) {
        return repository.accessUserRepository().findById(userId)
                .orElseThrow(ServiceMissingDataException::new);
    }

    public User getUserByLoginName(String login) {
        return repository.accessUserRepository().findByLoginName(login)
                .orElseThrow(ServiceMissingDataException::new);
    }

    public Item getItemById(Long itemId) {
        return repository.accessItemRepository().findById(itemId)
                .orElseThrow(ServiceMissingDataException::new);
    }

    public Item getItemByName(String itemName) {
        return repository.accessItemRepository().findByName(itemName)
                .orElseThrow(ServiceMissingDataException::new);
    }

    public Cart getOpenCartByUserId(Long userId) {
        return repository.accessCartRepository().findOpenCartForUserId(userId)
                .orElseThrow(ServiceMissingDataException::new);
    }

    public CartItem getCartItemByCartIdAndItemId(Long cartId, Long itemId) {
        return repository.accessCartItemRepository().findByCartIdAndItemId(cartId, itemId)
                .orElseThrow(ServiceMissingDataException::new);
    }

}
