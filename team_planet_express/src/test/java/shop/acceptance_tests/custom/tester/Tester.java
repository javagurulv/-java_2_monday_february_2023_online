package shop.acceptance_tests.custom.tester;

import org.springframework.beans.factory.annotation.Autowired;
import shop.core.database.CartItemRepository;
import shop.core.database.CartRepository;
import shop.core.database.ItemRepository;
import shop.core.domain.cart_item.CartItem;
import shop.core.domain.cart_item.CartItemConverter;
import shop.core.services.actions.shared.SecurityServiceImpl;
import shop.core_api.dto.cart_item.CartItemDTO;
import shop.core_api.dto.item.ItemDTO;

import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

public abstract class Tester {

    @Autowired
    protected ItemRepository itemRepository;
    @Autowired
    protected CartRepository cartRepository;
    @Autowired
    protected CartItemRepository cartItemRepository;
    @Autowired
    protected SecurityServiceImpl securityService;

    protected Tester checkItemInCart(CartItemDTO cartItemDTO) {
        CartItem cartItem = CartItemConverter.toCartItem(cartItemDTO);
        Optional<CartItem> optionalCartItem = cartItemRepository.findById(cartItem.getId());
        assertTrue(optionalCartItem.isPresent());
        assertEquals(optionalCartItem.get(), cartItemDTO);
        return this;
    }

    @SuppressWarnings("UnusedReturnValue")
    protected Tester checkItemInShop(ItemDTO itemDTO) {
        assertTrue(itemRepository.findAll().stream()
                .anyMatch(item -> item.getName().equals(itemDTO.getName()) && item.getAvailableQuantity() == itemDTO.getAvailableQuantity()));
        return this;

    }

    @SuppressWarnings("UnusedReturnValue")
    protected Tester notItemInCart(CartItemDTO cartItemDTO) {
        CartItem cartItem = CartItemConverter.toCartItem(cartItemDTO);
        Optional<CartItem> optionalCartItem = cartItemRepository.findById(cartItem.getId());
        assertTrue(optionalCartItem.isPresent());
        return this;
    }

}
