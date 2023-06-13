package shop.matchers;

import org.mockito.ArgumentMatcher;
import shop.core.domain.Cart;
import shop.core.domain.CartItem;
import shop.core.domain.Item;

public class CartItemMatcher implements ArgumentMatcher<CartItem> {

    private final Cart cart;
    private final Item item;
    private final Integer orderedQuantity;

    public CartItemMatcher(Cart cart, Item item, Integer orderedQuantity) {
        this.cart = cart;
        this.item = item;
        this.orderedQuantity = orderedQuantity;
    }

    @Override
    public boolean matches(CartItem cartItem) {
        return cart.equals(cartItem.getCart()) &&
                item.equals(cartItem.getItem()) &&
                orderedQuantity.equals(cartItem.getOrderedQuantity());
    }

}
