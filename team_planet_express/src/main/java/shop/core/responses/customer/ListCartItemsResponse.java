package shop.core.responses.customer;

import lombok.Getter;
import shop.core.dtos.CartItemDto;
import shop.core.responses.CoreError;
import shop.core.responses.CoreResponse;

import java.util.List;

@Getter
public class ListCartItemsResponse extends CoreResponse {

    private final List<CartItemDto> cartItems;

    public ListCartItemsResponse(List<CartItemDto> cartItems, List<CoreError> errors) {
        super(errors);
        this.cartItems = cartItems;
    }

}
