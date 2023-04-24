package shop.core.services.validators.universal.system;

import shop.core.database.Database;
import shop.core.domain.cart.Cart;
import shop.core.domain.cart_item.CartItem;
import shop.core.domain.item.Item;
import shop.core.domain.user.User;
import shop.core.services.exception.ServiceMissingDataException;
import shop.dependency_injection.DIComponent;
import shop.dependency_injection.DIDependency;

@DIComponent
public class DatabaseAccessValidator {

    @DIDependency
    private Database database;

    public User getUserById(Long userId) {
        return database.accessUserDatabase().findById(userId)
                .orElseThrow(ServiceMissingDataException::new);
    }

    public User getUserByLoginName(String login) {
        return database.accessUserDatabase().findByLoginName(login)
                .orElseThrow(ServiceMissingDataException::new);
    }

    public Item getItemById(Long itemId) {
        return database.accessItemDatabase().findById(itemId)
                .orElseThrow(ServiceMissingDataException::new);
    }

    public Item getItemByName(String itemName) {
        return database.accessItemDatabase().findByName(itemName)
                .orElseThrow(ServiceMissingDataException::new);
    }

    public Cart getOpenCartByUserId(Long userId) {
        return database.accessCartDatabase().findOpenCartForUserId(userId)
                .orElseThrow(ServiceMissingDataException::new);
    }

    public CartItem getCartItemByCartIdAndItemId(Long cartId, Long itemId) {
        return database.accessCartItemDatabase().findByCartIdAndItemId(cartId, itemId)
                .orElseThrow(ServiceMissingDataException::new);
    }

}
