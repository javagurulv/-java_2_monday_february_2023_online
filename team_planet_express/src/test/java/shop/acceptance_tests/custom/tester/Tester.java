package shop.acceptance_tests.custom.tester;

import org.springframework.context.ApplicationContext;
import shop.core.database.Repository;
import shop.core.domain.cart.Cart;
import shop.core.domain.cart_item.CartItem;
import shop.core.support.CurrentUser;

import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

public abstract class Tester {

    protected final ApplicationContext applicationContext;

    public Tester(ApplicationContext applicationContext) {
        this.applicationContext = applicationContext;
    }

    protected Tester checkItemInCart(String itemName, Integer quantity) {
        Optional<Cart> cart = getDatabase().accessCartDatabase().findOpenCartForUserId(getCurrentUser().getUser());
        assertTrue(cart.isPresent());
        Optional<CartItem> cartItem = getDatabase().accessCartItemDatabase().findByCartIdAndItemId(
                cart.get(),
                getDatabase().accessItemDatabase().findByName(itemName).orElseThrow()
        );
        assertTrue(cartItem.isPresent());
        assertEquals(quantity, cartItem.get().getOrderedQuantity());
        return this;
    }

    protected Tester checkItemInShop(String itemName, int quantity) {
        assertTrue(getDatabase().accessItemDatabase().getAllItems().stream()
                .anyMatch(item -> item.getName().equals(itemName) && item.getAvailableQuantity() == quantity));
        return this;

    }

    protected Tester notItemInCart(String itemName) {
        Optional<Cart> cart = getDatabase().accessCartDatabase().findOpenCartForUserId(getCurrentUser().getUser());
        if (cart.isPresent()) {
            Optional<CartItem> cartItem = getDatabase().accessCartItemDatabase().findByCartIdAndItemId(
                    cart.get(),
                    getDatabase().accessItemDatabase().findByName(itemName).orElseThrow()
            );
            assertTrue(cartItem.isEmpty());
        }
        return this;
    }

    protected Repository getDatabase() {
        return applicationContext.getBean(Repository.class);
    }

    protected CurrentUser getCurrentUser() {
        return applicationContext.getBean(CurrentUser.class);
    }

}
