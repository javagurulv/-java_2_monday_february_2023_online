package shop.core.requests.shared;

import lombok.Value;
import shop.core.support.CurrentUser;

@Value
public class SignOutRequest {

    CurrentUser currentUser;

}
