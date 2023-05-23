package shop.core.requests.shared;

import lombok.Value;
import shop.core.support.CurrentUser;

@Value
public class SignInRequest {

    CurrentUser currentUser;
    String loginName;
    String password;

}
