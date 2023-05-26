package shop.matchers;

import org.mockito.ArgumentMatcher;
import shop.core.domain.cart.Cart;
import shop.core.domain.cart.CartStatus;

public class CartMatcher implements ArgumentMatcher<Cart> {

    private final Long userId;
    private final CartStatus cartStatus;

    public CartMatcher(Long userId, CartStatus cartStatus) {
        this.userId = userId;
        this.cartStatus = cartStatus;
    }

    @Override
    public boolean matches(Cart cart) {
        return userId.equals(cart.getUserId()) &&
                cartStatus.equals(cart.getStatus());
    }

}
