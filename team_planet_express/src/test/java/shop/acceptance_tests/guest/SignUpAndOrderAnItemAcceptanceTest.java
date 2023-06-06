package shop.acceptance_tests.guest;

import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;
import shop.core.database.CartItemRepository;
import shop.core.database.CartRepository;
import shop.core.database.ItemRepository;
import shop.core.services.actions.customer.AddItemToCartService;
import shop.core.services.actions.guest.SignUpService;
import shop.core.support.CurrentUserId;

@RunWith(SpringRunner.class)
@SpringBootTest
public class SignUpAndOrderAnItemAcceptanceTest {

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

//  Use security service to login logout

//    @Sql({"/testDatabaseTableCreation.sql", "/testDatabaseDataInsertion.sql"})
//    @Test
//    @Ignore
//    void shouldBecomeCustomerAndHaveAnItemInTheCart() {
//        SignUpResponse signUpResponse =
//                signUpService.execute(new SignUpRequest(currentUserId, "Brannigan", "captain", "password"));
//        assertFalse(signUpResponse.hasErrors());
//        User newUser = signUpResponse.getUser();
//        assertEquals(UserRole.CUSTOMER, newUser.getUserRole());
//        assertTrue(cartRepository.findOpenCartForUserId(newUser.getId()).isPresent());
//        Item orderedItem = itemRepository.findByName("Lightspeed Briefs").orElseThrow();
//        AddItemToCartResponse addItemToCartResponse =
//                addItemToCartService.execute(new AddItemToCartRequest(orderedItem.getName(), "1"));
//        assertFalse(addItemToCartResponse.hasErrors());
//        Cart userCart = cartRepository.findOpenCartForUserId(newUser.getId()).get();
//        List<CartItem> cartItems = cartItemRepository.getAllCartItemsForCartId(userCart.getId());
//        assertEquals(1, cartItems.size());
//        CartItem cartItem = cartItems.get(0);
//        Item originalItem = itemRepository.findById(cartItem.getItem().getId()).orElseThrow();
//        assertEquals("Lightspeed Briefs", originalItem.getName());
//        assertEquals(1, cartItem.getOrderedQuantity());
//        assertEquals(2, originalItem.getAvailableQuantity());
//    }

}
