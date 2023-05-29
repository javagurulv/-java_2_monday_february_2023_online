package shop.acceptance_tests.custom.tester;

import org.springframework.beans.factory.annotation.Autowired;
import shop.core.database.Database;
import shop.core.domain.cart.Cart;
import shop.core.domain.cart_item.CartItem;
import shop.core.support.CurrentUserId;

import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

public abstract class Tester {

    @Autowired
    protected CurrentUserId currentUserId;
    @Autowired
    protected Database database;

    protected Tester checkItemInCart(String itemName, Integer quantity) {
        Optional<Cart> cart = database.accessCartDatabase().findOpenCartForUserId(currentUserId.getValue());
        assertTrue(cart.isPresent());
        Optional<CartItem> cartItem = database.accessCartItemDatabase().findByCartIdAndItemId(
                cart.get().getId(),
                database.accessItemDatabase().findByName(itemName).orElseThrow().getId()
        );
        assertTrue(cartItem.isPresent());
        assertEquals(quantity, cartItem.get().getOrderedQuantity());
        return this;
    }

    protected Tester checkItemInShop(String itemName, int quantity) {
        assertTrue(database.accessItemDatabase().getAllItems().stream()
                .anyMatch(item -> item.getName().equals(itemName) && item.getAvailableQuantity() == quantity));
        return this;

    }

    protected Tester notItemInCart(String itemName) {
        Optional<Cart> cart = database.accessCartDatabase().findOpenCartForUserId(currentUserId.getValue());
        if (cart.isPresent()) {
            Optional<CartItem> cartItem = database.accessCartItemDatabase().findByCartIdAndItemId(
                    cart.get().getId(),
                    database.accessItemDatabase().findByName(itemName).orElseThrow().getId()
            );
            assertTrue(cartItem.isEmpty());
        }
        return this;
    }

}
