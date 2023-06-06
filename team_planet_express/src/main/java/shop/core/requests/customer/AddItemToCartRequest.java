package shop.core.requests.customer;

import lombok.Value;

@Value
public class AddItemToCartRequest {

    String itemName;
    String orderedQuantity;

}
