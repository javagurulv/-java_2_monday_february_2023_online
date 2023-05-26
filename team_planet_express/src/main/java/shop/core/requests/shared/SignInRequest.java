package shop.core.requests.shared;

import lombok.Value;
import shop.core.support.CurrentUserId;

@Value
public class SignInRequest {

    CurrentUserId currentUserId;
    String loginName;
    String password;

}
