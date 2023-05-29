package shop.core.requests.customer;

import lombok.Value;
import shop.core.support.CurrentUserId;

@Value
public class AddItemToCartRequest {

    CurrentUserId currentUserId;
    String itemName;
    String orderedQuantity;

}
