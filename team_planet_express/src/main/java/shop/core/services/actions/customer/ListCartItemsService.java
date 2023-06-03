package shop.core.services.actions.customer;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;
import shop.core.database.CartItemRepository;
import shop.core.domain.cart.Cart;
import shop.core.domain.cart_item.CartItem;
import shop.core.domain.item.Item;
import shop.core.requests.customer.ListCartItemsRequest;
import shop.core.responses.CoreError;
import shop.core.responses.customer.ListCartItemsResponse;
import shop.core.services.cart.CartService;
import shop.core.services.validators.actions.customer.ListCartItemValidator;
import shop.core.services.validators.universal.system.DatabaseAccessValidator;
import shop.core.support.CartItemForList;

import java.math.BigDecimal;
import java.util.List;

@Component
@Transactional
public class ListCartItemsService {

    @Autowired
    private CartItemRepository cartItemRepository;
    @Autowired
    private ListCartItemValidator validator;
    @Autowired
    private DatabaseAccessValidator databaseAccessValidator;
    @Autowired
    private CartService cartService;

    public ListCartItemsResponse execute(ListCartItemsRequest request) {
        List<CoreError> errors = validator.validate(request);
        if (!errors.isEmpty()) {
            return new ListCartItemsResponse(errors);
        }
        Cart cart = databaseAccessValidator.getOpenCartByUserId(request.getUser().get().getId());
        List<CartItem> cartItems = cartItemRepository.getAllCartItemsForCartId(cart.getId());
        BigDecimal cartTotal = cartService.getSum(cart.getId());
        return new ListCartItemsResponse(cartItems, cartTotal);
    }

    private CartItemForList createCartItemForList(CartItem cartItem) {
        Item item = databaseAccessValidator.getItemById(cartItem.getItem().getId());
        return new CartItemForList(item.getName(), item.getPrice(), cartItem.getOrderedQuantity());
    }

}
