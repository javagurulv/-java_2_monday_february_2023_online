package shop.core_api.requests.customer;

import lombok.Value;
import shop.core_api.dto.cart_item.CartItemDTO;

@Value
public class AddItemToCartRequest {

    CartItemDTO cartItemDTO;

}
