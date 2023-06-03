package shop.core.requests.customer;

import lombok.Value;
import shop.core.domain.user.User;

@Value
public class RemoveItemFromCartRequest {

    User user;
    String itemName;

}
