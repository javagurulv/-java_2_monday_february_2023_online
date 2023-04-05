package Shop.core.requests.shared;

import Shop.core.support.MutableLong;
import lombok.Value;

@Value
public class SignInRequest {

    MutableLong userId;
    String loginName;
    String password;

}
