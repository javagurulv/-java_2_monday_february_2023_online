package shop.core.requests.customer;

import lombok.Value;

@Value
public class RemoveItemFromCartRequest {
    String itemName;
}
