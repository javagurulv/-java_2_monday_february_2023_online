package Shop.core.requests.shared;

import Shop.core.support.MutableLong;
import lombok.Value;

@Value
public class SignOutRequest {

    MutableLong userId;

}
