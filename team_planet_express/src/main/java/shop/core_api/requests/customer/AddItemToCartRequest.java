package shop.core_api.requests.customer;

import lombok.Value;

@Value
public class AddItemToCartRequest {

    String itemName;
    String orderedQuantity;

}
