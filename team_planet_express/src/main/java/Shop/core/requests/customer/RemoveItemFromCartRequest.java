package Shop.core.requests.customer;

import Shop.core.support.MutableLong;
import lombok.Value;

@Value
public class RemoveItemFromCartRequest {

    MutableLong userId;
    String itemName;

}
