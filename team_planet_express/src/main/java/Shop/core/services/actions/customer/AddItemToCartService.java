package Shop.core.services.actions.customer;

import Shop.core.database.Database;
import Shop.core.domain.cart.Cart;
import Shop.core.domain.cart_item.CartItem;
import Shop.core.domain.item.Item;
import Shop.core.requests.customer.AddItemToCartRequest;
import Shop.core.responses.CoreError;
import Shop.core.responses.customer.AddItemToCartResponse;
import Shop.core.services.exception.ServiceMissingDataException;
import Shop.core.services.validators.actions.customer.AddItemToCartValidator;

import java.util.List;
import java.util.Optional;

public class AddItemToCartService {

    private final Database database;
    private final AddItemToCartValidator validator;

    public AddItemToCartService(Database database, AddItemToCartValidator validator) {
        this.database = database;
        this.validator = validator;
    }

    public AddItemToCartResponse execute(AddItemToCartRequest request) {
        List<CoreError> errors = validator.validate(request);
        if (!errors.isEmpty()) {
            return new AddItemToCartResponse(errors);
        }
        Cart cart = getOpenCartForUserId(request.getUserId().getValue());
        Item item = getItemByName(request.getItemName());
        Integer orderedQuantity = Integer.parseInt(request.getOrderedQuantity());
        addItemToCart(cart, item, orderedQuantity);
        changeItemAvailability(item, orderedQuantity);
        return new AddItemToCartResponse();
    }

    private void addItemToCart(Cart cart, Item item, Integer orderedQuantity) {
        Optional<CartItem> cartItem = database.accessCartItemDatabase().findByCartIdAndItemId(cart.getId(), item.getId());
        if (cartItem.isEmpty()) {
            database.accessCartItemDatabase().save(new CartItem(cart.getId(), item.getId(), orderedQuantity));
        } else {
            Integer newCartItemQuantity = cartItem.get().getOrderedQuantity() + orderedQuantity;
            cartItem.get().setOrderedQuantity(newCartItemQuantity);
        }
    }

    private void changeItemAvailability(Item item, Integer orderedQuantity) {
        Integer newAvailableQuantity = item.getAvailableQuantity() - orderedQuantity;
        database.accessItemDatabase().changeAvailableQuantity(item.getId(), newAvailableQuantity);
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

}