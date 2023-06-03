package shop.core.requests.shared;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import shop.core.support.CurrentUserId;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class SignInRequest {

    private CurrentUserId currentUserId;
    private String loginName;
    private String password;

}
