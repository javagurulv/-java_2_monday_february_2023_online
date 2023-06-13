package shop.core.converters;

import org.springframework.stereotype.Component;
import shop.core.domain.CartItem;
import shop.core.dtos.CartItemDto;

import java.util.List;

@Component
public class CartItemConverter {

    public CartItemDto toCartItemDto(CartItem cartItem) {
        return new CartItemDto(
                cartItem.getCart().getUser().getLogin(),
                cartItem.getItem().getName(),
                cartItem.getItem().getPrice(),
                cartItem.getOrderedQuantity());
    }

    public List<CartItemDto> toCartItemDto(List<CartItem> cartItems) {
        return cartItems.stream()
                .map(this::toCartItemDto)
                .toList();
    }

}
