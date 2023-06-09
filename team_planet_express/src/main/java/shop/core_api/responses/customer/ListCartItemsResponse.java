package shop.core_api.responses.customer;

import lombok.Getter;
import shop.core.domain.cart_item.CartItem;
import shop.core_api.dto.cart_item.CartItemDTO;
import shop.core_api.responses.CoreError;
import shop.core_api.responses.CoreResponse;

import java.math.BigDecimal;
import java.util.List;

@Getter
public class ListCartItemsResponse extends CoreResponse {

    private List<CartItemDTO> cartItemsDTO;
    private BigDecimal cartTotal;

    public ListCartItemsResponse(List<CartItem> cartItems, BigDecimal cartTotal) {
        this.cartItemsDTO = cartItems.stream().map(CartItemDTO::of).toList();
        //TODO cartTotal needs another place to call home
        this.cartTotal = cartTotal;
    }

    public ListCartItemsResponse(List<CoreError> errors) {
        super(errors);
    }

}
