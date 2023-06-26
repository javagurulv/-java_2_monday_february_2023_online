package shop.core.converters;

import org.springframework.stereotype.Component;
import shop.core.domain.Cart;
import shop.core.dtos.CartDto;

@Component
public class CartConverter {

    public CartDto toCartDto(Cart cart) {
        return new CartDto(
                cart.getUser().getLogin(),
                cart.getStatus().toString(),
                cart.getLastUpdate());
    }

}
