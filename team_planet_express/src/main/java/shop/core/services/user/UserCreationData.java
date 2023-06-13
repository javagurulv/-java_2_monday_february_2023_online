package shop.core.services.user;

import lombok.Value;
import shop.core.enums.UserRole;

@Value
public class UserCreationData {

    String name;
    String loginName;
    String password;
    UserRole userRole;

}
