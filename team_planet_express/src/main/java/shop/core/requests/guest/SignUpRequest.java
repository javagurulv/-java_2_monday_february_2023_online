package shop.core.requests.guest;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import shop.core.support.CurrentUserId;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class SignUpRequest {

    private CurrentUserId currentUserId;
    private String name;
    private String loginName;
    private String password;

}
