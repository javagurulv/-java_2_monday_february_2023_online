package shop.acceptance_tests.guest;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.jdbc.Sql;
import shop.core.database.CartItemRepository;
import shop.core.database.CartRepository;
import shop.core.database.ItemRepository;
import shop.core.database.UserRepository;
import shop.core.domain.Cart;
import shop.core.domain.CartItem;
import shop.core.domain.Item;
import shop.core.domain.User;
import shop.core.dtos.UserDto;
import shop.core.enums.UserRole;
import shop.core.requests.customer.AddItemToCartRequest;
import shop.core.requests.guest.SignUpRequest;
import shop.core.responses.customer.AddItemToCartResponse;
import shop.core.responses.guest.SignUpResponse;
import shop.core.services.actions.customer.AddItemToCartService;
import shop.core.services.actions.guest.SignUpService;
import shop.core.support.CurrentUserId;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
public class SignUpAndOrderAnItemAcceptanceTest {

    @Autowired
    private UserRepository userRepository;
    @Autowired
    private ItemRepository itemRepository;
    @Autowired
    private CartRepository cartRepository;
    @Autowired
    private CartItemRepository cartItemRepository;
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
        UserDto newUserDto = signUpResponse.getUser();
        User newUser = userRepository.findByLoginName(newUserDto.getLogin()).orElseThrow();
        assertEquals(UserRole.CUSTOMER, newUser.getUserRole());
        assertTrue(cartRepository.findOpenCartForUserId(newUser.getId()).isPresent());
        Item orderedItem = itemRepository.findByName("Lightspeed Briefs").orElseThrow();
        AddItemToCartResponse addItemToCartResponse =
                addItemToCartService.execute(new AddItemToCartRequest(currentUserId, orderedItem.getName(), "1"));
        assertFalse(addItemToCartResponse.hasErrors());
        Cart userCart = cartRepository.findOpenCartForUserId(newUser.getId()).get();
        List<CartItem> cartItems = cartItemRepository.getAllCartItemsForCartId(userCart.getId());
        assertEquals(1, cartItems.size());
        CartItem cartItem = cartItems.get(0);
        Item originalItem = itemRepository.findById(cartItem.getItem().getId()).orElseThrow();
        assertEquals("Lightspeed Briefs", originalItem.getName());
        assertEquals(1, cartItem.getOrderedQuantity());
        assertEquals(2, originalItem.getAvailableQuantity());
    }

}
