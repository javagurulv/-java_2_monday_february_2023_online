package shop.core.services.actions.customer;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import shop.core.database.CartItemRepository;
import shop.core.database.ItemRepository;
import shop.core.domain.cart_item.CartItem;
import shop.core.domain.cart_item.CartItemConverter;
import shop.core.services.validators.services_validators.customer.RemoveItemFromCartValidator;
import shop.core_api.entry_point.customer.RemoveItemFromCartService;
import shop.core_api.requests.customer.RemoveItemFromCartRequest;
import shop.core_api.responses.CoreError;
import shop.core_api.responses.customer.RemoveItemFromCartResponse;

import java.util.List;

@Service
@Transactional
public class RemoveItemFromCartServiceImpl implements RemoveItemFromCartService {

    @Autowired
    private ItemRepository itemRepository;
    @Autowired
    private CartItemRepository cartItemRepository;
    @Autowired
    private RemoveItemFromCartValidator validator;


    public RemoveItemFromCartResponse execute(RemoveItemFromCartRequest request) {
        List<CoreError> errors = validator.validate(request);
        if (!errors.isEmpty()) {
            return new RemoveItemFromCartResponse(errors);
        }
        CartItem cartItem = CartItemConverter.toCartItem(request.getCartItemDTO());
        cartItemRepository.delete(cartItem);
        return new RemoveItemFromCartResponse();
    }

}
