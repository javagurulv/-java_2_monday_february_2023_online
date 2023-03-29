package core.requests.guest;

import core.support.MutableLong;
import lombok.Value;

@Value
public class SignUpRequest {

    String name;
    String loginName;
    String password;
    MutableLong userId;

}
