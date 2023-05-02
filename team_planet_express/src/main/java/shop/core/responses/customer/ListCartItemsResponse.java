package shop.core.responses.customer;

import lombok.Getter;
import shop.core.responses.CoreError;
import shop.core.responses.CoreResponse;
import shop.core.support.CartItemForList;

import java.math.BigDecimal;
import java.util.List;

@Getter
public class ListCartItemsResponse extends CoreResponse {

    private List<CartItemForList> cartItemsForList;
    private BigDecimal cartTotal;

    public ListCartItemsResponse(List<CartItemForList> cartItemsForList, BigDecimal cartTotal) {
        this.cartItemsForList = cartItemsForList;
        //TODO cartTotal needs another place to call home
        this.cartTotal = cartTotal;
    }

    public ListCartItemsResponse(List<CoreError> errors) {
        super(errors);
    }

}
