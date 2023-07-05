package shop.core_api.requests.customer;

import lombok.Value;
import shop.core_api.dto.cart.CartDTO;

@Value
public class BuyRequest {
    CartDTO cartDTO;
}
