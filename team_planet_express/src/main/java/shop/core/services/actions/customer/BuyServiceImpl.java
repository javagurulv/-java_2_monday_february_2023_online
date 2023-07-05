package shop.core.services.actions.customer;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import shop.core.database.CartItemRepository;
import shop.core.database.CartRepository;
import shop.core.domain.cart.Cart;
import shop.core.domain.cart.CartStatus;
import shop.core.domain.cart_item.CartItem;
import shop.core.domain.cart_item.CartItem_;
import shop.core.domain.user.User;
import shop.core.services.actions.shared.SecurityServiceImpl;
import shop.core.services.validators.services_validators.customer.BuyValidator;
import shop.core_api.entry_point.customer.BuyService;
import shop.core_api.requests.customer.BuyRequest;
import shop.core_api.responses.CoreError;
import shop.core_api.responses.customer.BuyResponse;

import java.util.List;

import static shop.core.database.specifications.CartItemSpecs.findBy;
import static shop.core.database.specifications.CartSpecs.findOpenCartForUser;

@Service
@Transactional
public class BuyServiceImpl implements BuyService {

    @Autowired
    private CartRepository cartRepository;
    @Autowired
    private CartItemRepository cartItemRepository;
    @Autowired
    private BuyValidator validator;
    @Autowired
    private SecurityServiceImpl securityService;


    public BuyResponse execute(BuyRequest request) {
        List<CoreError> errors = validator.validate(request);
        if (!errors.isEmpty()) {
            return new BuyResponse(errors);
        }
        User user = securityService.getAuthenticatedUserFromDB().get();
        Cart cart = cartRepository.findOne(findOpenCartForUser(user)).get();
        return buyCart(cart);
    }

    private BuyResponse buyCart(Cart cart) {
        List<CartItem> cartItemsToBuy = cartItemRepository.findAll(findBy(CartItem_.CART, cart));
        cartItemsToBuy.stream().forEach(
                cartItem -> cartItem.getItem()
                        .setAvailableQuantity(
                                cartItem.getItem().getAvailableQuantity() - cartItem.getOrderedQuantity()));
        cart.setStatus(CartStatus.CLOSED);
        cartRepository.save(new Cart(securityService.getAuthenticatedUserFromDB().get()));
        return new BuyResponse();
    }
}
