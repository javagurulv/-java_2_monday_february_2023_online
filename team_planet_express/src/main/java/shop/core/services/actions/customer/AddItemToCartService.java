package shop.core.services.actions.customer;

import shop.core.database.Database;
import shop.core.domain.cart.Cart;
import shop.core.domain.cart_item.CartItem;
import shop.core.domain.item.Item;
import shop.core.requests.customer.AddItemToCartRequest;
import shop.core.responses.CoreError;
import shop.core.responses.customer.AddItemToCartResponse;
import shop.core.services.validators.actions.customer.AddItemToCartValidator;
import shop.core.services.validators.universal.system.DatabaseAccessValidator;
import shop.dependency_injection.DIComponent;
import shop.dependency_injection.DIDependency;

import java.util.List;
import java.util.Optional;

@DIComponent
public class AddItemToCartService {

    @DIDependency
    private Database database;
    @DIDependency
    private AddItemToCartValidator validator;
    @DIDependency
    private DatabaseAccessValidator databaseAccessValidator;

    public AddItemToCartResponse execute(AddItemToCartRequest request) {
        List<CoreError> errors = validator.validate(request);
        if (!errors.isEmpty()) {
            return new AddItemToCartResponse(errors);
        }
        Cart cart = databaseAccessValidator.getOpenCartByUserId(request.getUserId().getValue());
        Item item = databaseAccessValidator.getItemByName(request.getItemName());
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

}
