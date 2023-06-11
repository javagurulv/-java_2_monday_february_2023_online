package shop.core.services.actions.customer;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import shop.core.database.CartItemRepository;
import shop.core.domain.cart.Cart;
import shop.core.domain.cart_item.CartItem;
import shop.core.domain.item.Item;
import shop.core.domain.user.User;
import shop.core.services.actions.shared.SecurityServiceImpl;
import shop.core.services.cart.CartService;
import shop.core.services.validators.actions.customer.ListCartItemValidator;
import shop.core.services.validators.universal.system.DatabaseAccessValidator;
import shop.core.support.CartItemForList;
import shop.core_api.entry_point.customer.GetListCartItemsService;
import shop.core_api.requests.customer.GetListCartItemsRequest;
import shop.core_api.responses.CoreError;
import shop.core_api.responses.customer.GetListCartItemsResponse;

import java.math.BigDecimal;
import java.util.List;
import java.util.Optional;

@Service
@Transactional
public class GetListCartItemsServiceImpl implements GetListCartItemsService {

    @Autowired
    private CartItemRepository cartItemRepository;
    @Autowired
    private ListCartItemValidator validator;
    @Autowired
    private DatabaseAccessValidator databaseAccessValidator;
    @Autowired
    private CartService cartService;
    @Autowired
    private SecurityServiceImpl securityService;

    public GetListCartItemsResponse execute(GetListCartItemsRequest request) {
        List<CoreError> errors = validator.validate(request);
        if (!errors.isEmpty()) {
            return new GetListCartItemsResponse(errors);
        }
        Optional<User> user = securityService.getAuthenticatedUserFromDB();
        Cart cart = databaseAccessValidator.getOpenCartByUserId(user.get().getId());
        List<CartItem> cartItems = cartItemRepository.getAllCartItemsForCartId(cart.getId());
        BigDecimal cartTotal = cartService.getSum(cart.getId());
        return new GetListCartItemsResponse(cartItems, cartTotal);
    }

    private CartItemForList createCartItemForList(CartItem cartItem) {
        Item item = databaseAccessValidator.getItemById(cartItem.getItem().getId());
        return new CartItemForList(item.getName(), item.getPrice(), cartItem.getOrderedQuantity());
    }

}
