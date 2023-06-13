package shop.core.services.actions.customer;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;
import shop.core.database.CartItemRepository;
import shop.core.database.ItemRepository;
import shop.core.domain.Cart;
import shop.core.domain.CartItem;
import shop.core.domain.Item;
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
    private ItemRepository itemRepository;
    @Autowired
    private CartItemRepository cartItemRepository;
    @Autowired
    private RemoveItemFromCartValidator validator;
    @Autowired
    private DatabaseAccessValidator databaseAccessValidator;


    public RemoveItemFromCartResponse execute(RemoveItemFromCartRequest request) {
        List<CoreError> errors = validator.validate(request);
        if (!errors.isEmpty()) {
            return new RemoveItemFromCartResponse(errors);
        }
        Cart cart = databaseAccessValidator.getOpenCartByUserId(request.getCurrentUserId().getValue());
        Item item = databaseAccessValidator.getItemByName(request.getItemName());
        CartItem cartItem = databaseAccessValidator.getCartItemByCartIdAndItemId(cart.getId(), item.getId());
        Integer newAvailableQuantity = item.getAvailableQuantity() + cartItem.getOrderedQuantity();
        cartItemRepository.deleteByID(cartItem.getId());
        itemRepository.changeAvailableQuantity(item.getId(), newAvailableQuantity);
        return new RemoveItemFromCartResponse();
    }

}
