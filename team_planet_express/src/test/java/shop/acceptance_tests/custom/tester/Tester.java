package shop.acceptance_tests.custom.tester;

import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import shop.config.ShopConfiguration;
import shop.core.database.CartDatabase;
import shop.core.database.CartItemDatabase;
import shop.core.database.ItemDatabase;
import shop.core.database.UserDatabase;
import shop.core.domain.cart.Cart;
import shop.core.domain.cart_item.CartItem;
import shop.core.support.CurrentUserId;

import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

@ExtendWith(SpringExtension.class)
@ContextConfiguration(classes = {ShopConfiguration.class})
public abstract class Tester {

    @Autowired
    protected ApplicationContext applicationContext;
    @Autowired
    protected CurrentUserId currentUserId;
    @Autowired
    protected ItemDatabase itemDatabase;
    @Autowired
    protected CartItemDatabase cartItemDatabase;
    @Autowired
    protected UserDatabase userDatabase;
    @Autowired
    protected CartDatabase cartDatabase;

    protected Tester checkItemInCart(String itemName, Integer quantity) {
        Optional<Cart> cart = cartDatabase.findOpenCartForUserId(currentUserId.getValue());
        assertTrue(cart.isPresent());
        Optional<CartItem> cartItem = cartItemDatabase.findByCartIdAndItemId(
                cart.get().getId(),
                itemDatabase.findByName(itemName).orElseThrow().getId()
        );
        assertTrue(cartItem.isPresent());
        assertEquals(quantity, cartItem.get().getOrderedQuantity());
        return this;
    }

    protected Tester checkItemInShop(String itemName, int quantity) {
        assertTrue(itemDatabase.getAllItems().stream()
                .anyMatch(item -> item.getName().equals(itemName) && item.getAvailableQuantity() == quantity));
        return this;

    }

    protected Tester notItemInCart(String itemName) {
        Optional<Cart> cart = cartDatabase.findOpenCartForUserId(currentUserId.getValue());
        if (cart.isPresent()) {
            Optional<CartItem> cartItem = cartItemDatabase.findByCartIdAndItemId(
                    cart.get().getId(),
                    itemDatabase.findByName(itemName).orElseThrow().getId()
            );
            assertTrue(cartItem.isEmpty());
        }
        return this;
    }

}
