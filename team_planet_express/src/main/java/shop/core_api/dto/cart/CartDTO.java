package shop.core_api.dto.cart;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import shop.core.domain.cart.Cart;
import shop.core.domain.cart.CartStatus;
import shop.core.domain.user.User;

import java.time.LocalDateTime;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class CartDTO {

    private Long id;
    private Long userId;
    private CartDTOStatus status;
    private LocalDateTime lastUpdate;

    public Cart toCart() {
        return CartDTO.toCart(this);
    }

    public static Cart toCart(CartDTO cartDTO) {
        Cart cart = new Cart();
        cart.setId(cartDTO.getId());
        User user = new User();
        user.setId(cartDTO.getUserId());
        cart.setUser(user);
        cart.setStatus(CartStatus.valueOf(cartDTO.getStatus().name()));
        return cart;
    }

    public static CartDTO of(Cart cart) {
        CartDTO cartDTO = new CartDTO(
                cart.getId(),
                cart.getUser().getId(),
                CartDTOStatus.valueOf(cart.getStatus().name()),
                cart.getLastUpdate()
        );
        return cartDTO;
    }
}
