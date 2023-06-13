package shop.matchers;

import org.mockito.ArgumentMatcher;
import shop.core.enums.UserRole;
import shop.core.services.user.UserCreationData;

public class UserCreationDataMatcher implements ArgumentMatcher<UserCreationData> {

    private final String name;
    private final String loginName;
    private final String password;
    private final UserRole userRole;

    public UserCreationDataMatcher(String name, String loginName, String password, UserRole userRole) {
        this.name = name;
        this.loginName = loginName;
        this.password = password;
        this.userRole = userRole;
    }

    @Override
    public boolean matches(UserCreationData userCreationData) {
        return name.equals(userCreationData.getName()) &&
                loginName.equals(userCreationData.getLoginName()) &&
                password.equals(userCreationData.getPassword()) &&
                userRole.equals(userCreationData.getUserRole());
    }

}
