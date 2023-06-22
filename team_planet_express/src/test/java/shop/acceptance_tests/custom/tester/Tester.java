package shop.acceptance_tests.custom.tester;

import org.springframework.beans.factory.annotation.Autowired;
import shop.core.database.jpa.JpaCartItemRepository;
import shop.core.database.jpa.JpaCartRepository;
import shop.core.database.jpa.JpaItemRepository;
import shop.core.domain.Cart;
import shop.core.domain.CartItem;
import shop.core.services.validators.universal.system.RepositoryAccessValidator;
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
    @Autowired
    protected RepositoryAccessValidator repositoryAccessValidator;

    protected Tester checkItemInCart(String itemName, Integer quantity) {
        Optional<Cart> cart = cartRepository.findOpenCartByUser(
                repositoryAccessValidator.getUserById(currentUserId.getValue()));
        assertTrue(cart.isPresent());
        Optional<CartItem> cartItem = cartItemRepository.findFirstByCartAndItem(
                cart.get(),
                repositoryAccessValidator.getItemByName(itemName)
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
        Optional<Cart> cart = cartRepository.findOpenCartByUser(
                repositoryAccessValidator.getUserById(currentUserId.getValue()));
        if (cart.isPresent()) {
            Optional<CartItem> cartItem = cartItemRepository.findFirstByCartAndItem(
                    cart.get(),
                    repositoryAccessValidator.getItemByName(itemName)
            ).stream().findFirst();
            assertTrue(cartItem.isEmpty());
        }
        return this;
    }

}
