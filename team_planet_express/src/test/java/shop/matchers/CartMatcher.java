package shop.matchers;

import org.mockito.ArgumentMatcher;
import shop.core.domain.cart.Cart;
import shop.core.domain.user.User;

public class CartMatcher implements ArgumentMatcher<Cart> {

    private final User user;
    private final String cartStatus;

    public CartMatcher(User user, String cartStatus) {
        this.user = user;
        this.cartStatus = cartStatus;
    }

    @Override
    public boolean matches(Cart cart) {
        return user.equals(cart.getUser()) &&
                cartStatus.equals(cart.getStatus());
    }

}
