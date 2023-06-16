package shop.acceptance_tests.custom.tester;

import org.springframework.beans.factory.annotation.Autowired;
import shop.core.database.jpa.JpaCartItemRepository;
import shop.core.database.jpa.JpaCartRepository;
import shop.core.database.jpa.JpaItemRepository;
import shop.core.domain.Cart;
import shop.core.domain.CartItem;
import shop.core.support.CurrentUserId;

import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

public abstract class Tester {

    @Autowired
    protected JpaItemRepository itemRepository;
    @Autowired
    protected JpaCartRepository cartRepository;
    @Autowired
    protected JpaCartItemRepository cartItemRepository;
    @Autowired
    protected CurrentUserId currentUserId;

    protected Tester checkItemInCart(String itemName, Integer quantity) {
        Optional<Cart> cart = cartRepository.findOpenCartByUserId(currentUserId.getValue());
        assertTrue(cart.isPresent());
        Optional<CartItem> cartItem = cartItemRepository.findByCartIdAndItemId(
                cart.get().getId(),
                itemRepository.findOneByName(itemName).orElseThrow().getId()
        ).stream().findFirst();
        assertTrue(cartItem.isPresent());
        assertEquals(quantity, cartItem.get().getOrderedQuantity());
        return this;
    }

    @SuppressWarnings("UnusedReturnValue")
    protected Tester checkItemInShop(String itemName, int quantity) {
        assertTrue(itemRepository.findAll().stream()
                .anyMatch(item -> item.getName().equals(itemName) && item.getAvailableQuantity() == quantity));
        return this;

    }

    @SuppressWarnings("UnusedReturnValue")
    protected Tester notItemInCart(String itemName) {
        Optional<Cart> cart = cartRepository.findOpenCartByUserId(currentUserId.getValue());
        if (cart.isPresent()) {
            Optional<CartItem> cartItem = cartItemRepository.findByCartIdAndItemId(
                    cart.get().getId(),
                    itemRepository.findOneByName(itemName).orElseThrow().getId()
            ).stream().findFirst();
            assertTrue(cartItem.isEmpty());
        }
        return this;
    }

}
