package shop.core.requests.guest;

import lombok.Value;
import shop.core.support.CurrentUser;

@Value
public class SignUpRequest {

    CurrentUser currentUser;
    String name;
    String loginName;
    String password;

}
