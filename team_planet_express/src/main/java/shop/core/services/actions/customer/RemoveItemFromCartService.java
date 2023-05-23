package shop.core.services.actions.customer;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;
import shop.core.database.Repository;
import shop.core.domain.cart.Cart;
import shop.core.domain.cart_item.CartItem;
import shop.core.domain.item.Item;
import shop.core.domain.user.User;
import shop.core.requests.customer.RemoveItemFromCartRequest;
import shop.core.responses.CoreError;
import shop.core.responses.customer.RemoveItemFromCartResponse;
import shop.core.services.validators.actions.customer.RemoveItemFromCartValidator;
import shop.core.services.validators.universal.system.DatabaseAccessValidator;

import java.util.List;

@Component
@Transactional
public class RemoveItemFromCartService {

    @Autowired
    private Repository repository;
    @Autowired
    private RemoveItemFromCartValidator validator;
    @Autowired
    private DatabaseAccessValidator databaseAccessValidator;


    public RemoveItemFromCartResponse execute(RemoveItemFromCartRequest request) {
        List<CoreError> errors = validator.validate(request);
        if (!errors.isEmpty()) {
            return new RemoveItemFromCartResponse(errors);
        }
        Cart cart = databaseAccessValidator.getOpenCartByUserId(request.getUserId().getUser());
        Item item = databaseAccessValidator.getItemByName(request.getItemName());
        CartItem cartItem = databaseAccessValidator.getCartItemByCartIdAndItemId(cart, item);
        Integer newAvailableQuantity = item.getAvailableQuantity() + cartItem.getOrderedQuantity();
        repository.accessCartItemDatabase().deleteByID(cartItem.getId());
        repository.accessItemDatabase().changeAvailableQuantity(item.getId(), newAvailableQuantity);
        return new RemoveItemFromCartResponse();
    }

}
