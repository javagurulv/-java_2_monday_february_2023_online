package shop.core.domain.cart;

import shop.core.domain.user.User;
import shop.core_api.dto.cart.CartDTO;
import shop.core_api.dto.cart.CartDTOStatus;

public class CartConverter {

    public static Cart toCart(CartDTO cartDTO) {
        Cart cart = new Cart();
        cart.setId(cartDTO.getId());
        User user = new User();
        user.setId(cartDTO.getUserId());
        cart.setUser(user);
        cart.setStatus(CartStatus.valueOf(cartDTO.getStatus().name()));
        return cart;
    }

    public static CartDTO toCartDTO(Cart cart) {
        CartDTO cartDTO = new CartDTO(
                cart.getId(),
                cart.getUser().getId(),
                CartDTOStatus.valueOf(cart.getStatus().name()),
                cart.getLastUpdate()
        );
        return cartDTO;
    }
}
