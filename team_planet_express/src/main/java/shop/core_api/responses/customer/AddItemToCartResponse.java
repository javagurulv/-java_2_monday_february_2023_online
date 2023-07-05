package shop.core_api.responses.customer;

import lombok.Getter;
import shop.core.domain.cart_item.CartItem;
import shop.core.domain.cart_item.CartItemConverter;
import shop.core_api.dto.cart_item.CartItemDTO;
import shop.core_api.responses.CoreError;
import shop.core_api.responses.CoreResponse;

import java.util.List;

@Getter
public class AddItemToCartResponse extends CoreResponse {

    private CartItemDTO cartItemDTO;

    public AddItemToCartResponse(CartItem cartItem) {
        this.cartItemDTO = CartItemConverter.toCartItemDTO(cartItem);
    }

    public AddItemToCartResponse(List<CoreError> errors) {
        super(errors);
    }

}
