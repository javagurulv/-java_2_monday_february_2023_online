//package shop.acceptance_tests.custom.tester;
//
//import org.springframework.beans.factory.annotation.Autowired;
//import shop.core.database.CartItemRepository;
//import shop.core.database.CartRepository;
//import shop.core.database.ItemRepository;
//import shop.core.domain.cart.Cart;
//import shop.core.domain.cart_item.CartItem;
//import shop.core.support.CurrentUserId;
//
//import java.util.Optional;
//
//import static org.junit.jupiter.api.Assertions.assertEquals;
//import static org.junit.jupiter.api.Assertions.assertTrue;
//
//public abstract class Tester {
//
//    @Autowired
//    protected ItemRepository itemRepository;
//    @Autowired
//    protected CartRepository cartRepository;
//    @Autowired
//    protected CartItemRepository cartItemRepository;
//    @Autowired
//    protected CurrentUserId currentUserId;
//
//    protected Tester checkItemInCart(String itemName, Integer quantity) {
//        Optional<Cart> cart = cartRepository.findOpenCartForUserId(currentUserId.getValue());
//        assertTrue(cart.isPresent());
//        Optional<CartItem> cartItem = cartItemRepository.findByCartIdAndItemId(
//                cart.get().getId(),
//                itemRepository.findByName(itemName).orElseThrow().getId()
//        );
//        assertTrue(cartItem.isPresent());
//        assertEquals(quantity, cartItem.get().getOrderedQuantity());
//        return this;
//    }
//
//    @SuppressWarnings("UnusedReturnValue")
//    protected Tester checkItemInShop(String itemName, int quantity) {
//        assertTrue(itemRepository.getAllItems().stream()
//                .anyMatch(item -> item.getName().equals(itemName) && item.getAvailableQuantity() == quantity));
//        return this;
//
//    }
//
//    @SuppressWarnings("UnusedReturnValue")
//    protected Tester notItemInCart(String itemName) {
//        Optional<Cart> cart = cartRepository.findOpenCartForUserId(currentUserId.getValue());
//        if (cart.isPresent()) {
//            Optional<CartItem> cartItem = cartItemRepository.findByCartIdAndItemId(
//                    cart.get().getId(),
//                    itemRepository.findByName(itemName).orElseThrow().getId()
//            );
//            assertTrue(cartItem.isEmpty());
//        }
//        return this;
//    }
//
//}
