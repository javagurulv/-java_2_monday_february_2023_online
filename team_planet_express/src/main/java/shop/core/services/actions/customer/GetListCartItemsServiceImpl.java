package shop.core.services.actions.customer;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import shop.core.database.CartItemRepository;
import shop.core.database.CartRepository;
import shop.core.database.specifications.CartItemSpecs;
import shop.core.domain.cart.Cart;
import shop.core.domain.cart_item.CartItem;
import shop.core.domain.cart_item.CartItem_;
import shop.core.domain.user.User;
import shop.core.services.actions.shared.SecurityServiceImpl;
import shop.core.services.cart.CartService;
import shop.core.services.validators.services_validators.customer.ListCartItemValidator;
import shop.core_api.entry_point.customer.GetListCartItemsService;
import shop.core_api.requests.customer.GetListCartItemsRequest;
import shop.core_api.responses.CoreError;
import shop.core_api.responses.customer.GetListCartItemsResponse;

import java.math.BigDecimal;
import java.util.List;
import java.util.Optional;

import static shop.core.database.specifications.CartSpecs.findOpenCartForUser;

@Service
@Transactional
public class GetListCartItemsServiceImpl implements GetListCartItemsService {

    @Autowired
    private CartItemRepository cartItemRepository;
    @Autowired
    private CartRepository cartRepository;
    @Autowired
    private ListCartItemValidator validator;
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

        Cart cart = cartRepository.findOne(findOpenCartForUser(user.get())).get();
        List<CartItem> cartItems = cartItemRepository.findAll(CartItemSpecs.findBy(CartItem_.CART, cart));
        BigDecimal cartTotal = cartService.getSum(cart.getId());
        return new GetListCartItemsResponse(cartItems, cartTotal);
    }

}
