package shop.acceptance_tests.custom.tester;

import org.springframework.beans.factory.annotation.Autowired;
import shop.core.database.Repository;
import shop.core.domain.cart.Cart;
import shop.core.domain.cart_item.CartItem;
import shop.core.support.CurrentUserId;

import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

public abstract class Tester {

    @Autowired
    protected Repository repository;
    @Autowired
    protected CurrentUserId currentUserId;

    protected Tester checkItemInCart(String itemName, Integer quantity) {
        Optional<Cart> cart = repository.accessCartRepository().findOpenCartForUserId(currentUserId.getValue());
        assertTrue(cart.isPresent());
        Optional<CartItem> cartItem = repository.accessCartItemRepository().findByCartIdAndItemId(
                cart.get().getId(),
                repository.accessItemRepository().findByName(itemName).orElseThrow().getId()
        );
        assertTrue(cartItem.isPresent());
        assertEquals(quantity, cartItem.get().getOrderedQuantity());
        return this;
    }

    @SuppressWarnings("UnusedReturnValue")
    protected Tester checkItemInShop(String itemName, int quantity) {
        assertTrue(repository.accessItemRepository().getAllItems().stream()
                .anyMatch(item -> item.getName().equals(itemName) && item.getAvailableQuantity() == quantity));
        return this;

    }

    @SuppressWarnings("UnusedReturnValue")
    protected Tester notItemInCart(String itemName) {
        Optional<Cart> cart = repository.accessCartRepository().findOpenCartForUserId(currentUserId.getValue());
        if (cart.isPresent()) {
            Optional<CartItem> cartItem = repository.accessCartItemRepository().findByCartIdAndItemId(
                    cart.get().getId(),
                    repository.accessItemRepository().findByName(itemName).orElseThrow().getId()
            );
            assertTrue(cartItem.isEmpty());
        }
        return this;
    }

}
