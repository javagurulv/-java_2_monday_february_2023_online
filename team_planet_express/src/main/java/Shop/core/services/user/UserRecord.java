package Shop.core.services.user;

import Shop.core.domain.user.UserRole;

public record UserRecord(String name,
                         String loginName,
                         String password,
                         UserRole userRole) {

}
