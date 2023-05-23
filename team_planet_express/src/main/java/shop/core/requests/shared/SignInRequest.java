package shop.core.requests.shared;

import lombok.Value;
import shop.core.support.CurrentUser;

@Value
public class SignInRequest {

    CurrentUser userId;
    String loginName;
    String password;

}
