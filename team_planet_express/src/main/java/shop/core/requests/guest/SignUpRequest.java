package shop.core.requests.guest;

import lombok.Value;
import shop.core.support.CurrentUserId;

@Value
public class SignUpRequest {

    CurrentUserId currentUserId;
    String name;
    String loginName;
    String password;

}
