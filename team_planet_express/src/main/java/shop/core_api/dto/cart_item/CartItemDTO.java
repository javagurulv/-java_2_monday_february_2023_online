package shop.core_api.dto.cart_item;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import shop.core.domain.cart.Cart;
import shop.core.domain.cart_item.CartItem;
import shop.core_api.dto.item.ItemDTO;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class CartItemDTO {

    private Long id;
    private Long cartId;
    private ItemDTO itemDTO;
    private Integer orderedQuantity;


    public CartItem toCartItem() {
        return CartItemDTO.toCartItem(this);
    }

    public static CartItem toCartItem(CartItemDTO cartItemDTO) {
        CartItem cartItem = new CartItem();
        cartItem.setId(cartItemDTO.getId());
        Cart cart = new Cart();
        cart.setId(cartItemDTO.getCartId());
        cartItem.setCart(cart);
        cartItem.setItem(cartItemDTO.getItemDTO().toItem());
        cartItem.setOrderedQuantity(cartItemDTO.getOrderedQuantity());
        return cartItem;
    }

    public static CartItemDTO of(CartItem cartItem) {
        CartItemDTO cartItemDTO = new CartItemDTO(
                cartItem.getId(),
                cartItem.getCart().getId(),
                ItemDTO.of(cartItem.getItem()),
                cartItem.getOrderedQuantity()
        );
        return cartItemDTO;
    }

}
