package shop.acceptance_tests.guest;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.jdbc.Sql;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import shop.config.ShopConfiguration;
import shop.core.database.Database;
import shop.core.domain.cart.Cart;
import shop.core.domain.cart_item.CartItem;
import shop.core.domain.item.Item;
import shop.core.domain.user.User;
import shop.core.domain.user.UserRole;
import shop.core.requests.customer.AddItemToCartRequest;
import shop.core.requests.guest.SignUpRequest;
import shop.core.responses.customer.AddItemToCartResponse;
import shop.core.responses.guest.SignUpResponse;
import shop.core.services.actions.customer.AddItemToCartService;
import shop.core.services.actions.guest.SignUpService;
import shop.core.support.CurrentUserId;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

@ExtendWith(SpringExtension.class)
@ContextConfiguration(classes = {ShopConfiguration.class})
public class SignUpAndOrderAnItemAcceptanceTest {

    @Autowired
    private Database database;
    @Autowired
    private CurrentUserId currentUserId;
    @Autowired
    private SignUpService signUpService;
    @Autowired
    private AddItemToCartService addItemToCartService;

    @Sql({"/testDatabaseTableCreation.sql", "/testDatabaseDataInsertion.sql"})
    @Test
    void shouldBecomeCustomerAndHaveAnItemInTheCart() {
        SignUpResponse signUpResponse =
                signUpService.execute(new SignUpRequest(currentUserId, "Brannigan", "captain", "password"));
        assertFalse(signUpResponse.hasErrors());
        User newUser = signUpResponse.getUser();
        assertEquals(UserRole.CUSTOMER, newUser.getUserRole());
        assertTrue(database.accessCartDatabase().findOpenCartForUserId(newUser.getId()).isPresent());
        Item orderedItem = database.accessItemDatabase().findByName("Lightspeed Briefs").orElseThrow();
        AddItemToCartResponse addItemToCartResponse =
                addItemToCartService.execute(new AddItemToCartRequest(currentUserId, orderedItem.getName(), "1"));
        assertFalse(addItemToCartResponse.hasErrors());
        Cart userCart = database.accessCartDatabase().findOpenCartForUserId(newUser.getId()).get();
        List<CartItem> cartItems = database.accessCartItemDatabase().getAllCartItemsForCartId(userCart.getId());
        assertEquals(1, cartItems.size());
        CartItem cartItem = cartItems.get(0);
        Item originalItem = database.accessItemDatabase().findById(cartItem.getItemId()).orElseThrow();
        assertEquals("Lightspeed Briefs", originalItem.getName());
        assertEquals(1, cartItem.getOrderedQuantity());
        assertEquals(2, originalItem.getAvailableQuantity());
    }

}
