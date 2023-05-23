package shop.core.services.actions.customer;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;
import shop.core.database.Repository;
import shop.core.domain.cart.Cart;
import shop.core.domain.cart_item.CartItem;
import shop.core.domain.item.Item;
import shop.core.domain.user.User;
import shop.core.requests.customer.AddItemToCartRequest;
import shop.core.responses.CoreError;
import shop.core.responses.customer.AddItemToCartResponse;
import shop.core.services.validators.actions.customer.AddItemToCartValidator;
import shop.core.services.validators.universal.system.DatabaseAccessValidator;

import java.util.List;
import java.util.Optional;

@Component
@Transactional
public class AddItemToCartService {

    @Autowired
    private Repository repository;
    @Autowired
    private AddItemToCartValidator validator;
    @Autowired
    private DatabaseAccessValidator databaseAccessValidator;

    public AddItemToCartResponse execute(AddItemToCartRequest request) {
        List<CoreError> errors = validator.validate(request);
        if (!errors.isEmpty()) {
            return new AddItemToCartResponse(errors);
        }
        Cart cart = databaseAccessValidator.getOpenCartByUserId(request.getUserId().getUser());
        Item item = databaseAccessValidator.getItemByName(request.getItemName());
        Integer orderedQuantity = Integer.parseInt(request.getOrderedQuantity());
        addItemToCart(cart, item, orderedQuantity);
        changeItemAvailability(item, orderedQuantity);
        return new AddItemToCartResponse();
    }

    private void addItemToCart(Cart cart, Item item, Integer orderedQuantity) {
        Optional<CartItem> cartItem = repository.accessCartItemDatabase().findByCartIdAndItemId(cart, item);
        if (cartItem.isEmpty()) {
            repository.accessCartItemDatabase().save(new CartItem(cart, item, orderedQuantity));
        } else {
            Integer newCartItemQuantity = cartItem.get().getOrderedQuantity() + orderedQuantity;
            repository.accessCartItemDatabase().changeOrderedQuantity(cartItem.get().getId(), newCartItemQuantity);
        }
    }

    private void changeItemAvailability(Item item, Integer orderedQuantity) {
        Integer newAvailableQuantity = item.getAvailableQuantity() - orderedQuantity;
        repository.accessItemDatabase().changeAvailableQuantity(item.getId(), newAvailableQuantity);
    }

}
