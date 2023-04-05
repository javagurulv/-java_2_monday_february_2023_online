package Shop.core.services.actions.customer;

import Shop.core.database.Database;
import Shop.core.domain.cart.Cart;
import Shop.core.domain.cart_item.CartItem;
import Shop.core.domain.item.Item;
import Shop.core.requests.customer.RemoveItemFromCartRequest;
import Shop.core.responses.CoreError;
import Shop.core.responses.customer.RemoveItemFromCartResponse;
import Shop.core.services.exception.ServiceMissingDataException;
import Shop.core.services.validators.actions.customer.RemoveItemFromCartValidator;

import java.util.List;

public class RemoveItemFromCartService {

    private final Database database;
    private final RemoveItemFromCartValidator validator;

    public RemoveItemFromCartService(Database database, RemoveItemFromCartValidator validator) {
        this.database = database;
        this.validator = validator;
    }

    public RemoveItemFromCartResponse execute(RemoveItemFromCartRequest request) {
        List<CoreError> errors = validator.validate(request);
        if (!errors.isEmpty()) {
            return new RemoveItemFromCartResponse(errors);
        }
        Cart cart = getOpenCartForUserId(request.getUserId().getValue());
        Item item = getItemByName(request.getItemName());
        CartItem cartItem = getCartItemByCartIdAndItemId(cart.getId(), item.getId());
        Integer newAvailableQuantity = item.getAvailableQuantity() + cartItem.getOrderedQuantity();
        database.accessCartItemDatabase().deleteByID(cartItem.getId());
        database.accessItemDatabase().changeAvailableQuantity(item.getId(), newAvailableQuantity);
        return new RemoveItemFromCartResponse();
    }

    //TODO yeet, duplicate
    private Cart getOpenCartForUserId(Long userId) {
        return database.accessCartDatabase().findOpenCartForUserId(userId)
                .orElseThrow(ServiceMissingDataException::new);
    }

    //TODO yeet, duplicate
    private Item getItemByName(String itemName) {
        return database.accessItemDatabase().findByName(itemName)
                .orElseThrow(ServiceMissingDataException::new);
    }

    //TODO yeet, duplicate
    private CartItem getCartItemByCartIdAndItemId(Long cartId, Long itemId) {
        return database.accessCartItemDatabase().findByCartIdAndItemId(cartId, itemId)
                .orElseThrow(ServiceMissingDataException::new);
    }

}