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
        Optional<Cart> cart = getDatabase().accessCartRepository().findOpenCartForUserId(getCurrentUser().getUser());
        assertTrue(cart.isPresent());
        Optional<CartItem> cartItem = getDatabase().accessCartItemRepository().findByCartIdAndItemId(
                cart.get(),
                getDatabase().accessItemRepository().findByName(itemName).orElseThrow()
        );
        assertTrue(cartItem.isPresent());
        assertEquals(quantity, cartItem.get().getOrderedQuantity());
        return this;
    }

    protected Tester checkItemInShop(String itemName, int quantity) {
        assertTrue(getDatabase().accessItemRepository().getAllItems().stream()
                .anyMatch(item -> item.getName().equals(itemName) && item.getAvailableQuantity() == quantity));
        return this;

    }

    protected Tester notItemInCart(String itemName) {
        Optional<Cart> cart = getDatabase().accessCartRepository().findOpenCartForUserId(getCurrentUser().getUser());
        if (cart.isPresent()) {
            Optional<CartItem> cartItem = getDatabase().accessCartItemRepository().findByCartIdAndItemId(
                    cart.get(),
                    getDatabase().accessItemRepository().findByName(itemName).orElseThrow()
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
