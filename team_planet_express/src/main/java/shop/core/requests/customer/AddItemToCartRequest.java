package shop.core.requests.customer;

import lombok.Value;
import shop.core.domain.user.User;

@Value
public class AddItemToCartRequest {

    User user;
    String itemName;
    String orderedQuantity;

}
