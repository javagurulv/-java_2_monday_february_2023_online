package shop.core.domain.cart_item;

import shop.core.domain.cart.Cart;
import shop.core.domain.item.ItemConverter;
import shop.core_api.dto.cart_item.CartItemDTO;

public class CartItemConverter {
    public static CartItem toCartItem(CartItemDTO cartItemDTO) {
        CartItem cartItem = new CartItem();
        cartItem.setId(cartItemDTO.getId());
        Cart cart = new Cart();
        cart.setId(cartItemDTO.getCartId());
        cartItem.setCart(cart);
        cartItem.setItem(ItemConverter.toItem(cartItemDTO.getItemDTO()));
        cartItem.setOrderedQuantity(cartItemDTO.getOrderedQuantity());
        return cartItem;
    }

    public static CartItemDTO toCartItemDTO(CartItem cartItem) {
        CartItemDTO cartItemDTO = new CartItemDTO(
                cartItem.getId(),
                cartItem.getCart().getId(),
                ItemConverter.toItemDTO(cartItem.getItem()),
                cartItem.getOrderedQuantity()
        );
        return cartItemDTO;
    }
}
