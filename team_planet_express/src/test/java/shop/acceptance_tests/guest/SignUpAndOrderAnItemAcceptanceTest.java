package shop.acceptance_tests.guest;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.jdbc.Sql;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import shop.config.ShopConfiguration;
import shop.core.database.Repository;
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
    private Repository repository;
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
        assertTrue(repository.accessCartRepository().findOpenCartForUserId(newUser.getId()).isPresent());
        Item orderedItem = repository.accessItemRepository().findByName("Lightspeed Briefs").orElseThrow();
        AddItemToCartResponse addItemToCartResponse =
                addItemToCartService.execute(new AddItemToCartRequest(currentUserId, orderedItem.getName(), "1"));
        assertFalse(addItemToCartResponse.hasErrors());
        Cart userCart = repository.accessCartRepository().findOpenCartForUserId(newUser.getId()).get();
        List<CartItem> cartItems = repository.accessCartItemRepository().getAllCartItemsForCartId(userCart.getId());
        assertEquals(1, cartItems.size());
        CartItem cartItem = cartItems.get(0);
        Item originalItem = repository.accessItemRepository().findById(cartItem.getItem().getId()).orElseThrow();
        assertEquals("Lightspeed Briefs", originalItem.getName());
        assertEquals(1, cartItem.getOrderedQuantity());
        assertEquals(2, originalItem.getAvailableQuantity());
    }

}
