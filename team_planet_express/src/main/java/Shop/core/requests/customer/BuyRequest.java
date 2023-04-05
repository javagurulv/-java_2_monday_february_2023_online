package Shop.core.requests.customer;

import Shop.core.support.MutableLong;
import lombok.Value;

@Value
public class BuyRequest {

    MutableLong userId;

}
