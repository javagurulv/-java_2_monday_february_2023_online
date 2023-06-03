package shop.core.responses.customer;

import lombok.Getter;
import shop.core.domain.cart_item.CartItem;
import shop.core.responses.CoreError;
import shop.core.responses.CoreResponse;

import java.math.BigDecimal;
import java.util.List;

@Getter
public class ListCartItemsResponse extends CoreResponse {

    private List<CartItem> cartItems;
    private BigDecimal cartTotal;

    public ListCartItemsResponse(List<CartItem> cartItems, BigDecimal cartTotal) {
        this.cartItems = cartItems;
        //TODO cartTotal needs another place to call home
        this.cartTotal = cartTotal;
    }

    public ListCartItemsResponse(List<CoreError> errors) {
        super(errors);
    }

}
