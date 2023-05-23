package shop.matchers;

import org.mockito.ArgumentMatcher;
import shop.core.requests.shared.SignInRequest;
import shop.core.support.CurrentUser;

public class SignInRequestMatcher implements ArgumentMatcher<SignInRequest> {

    private final CurrentUser userId;
    private final String loginName;
    private final String password;

    public SignInRequestMatcher(CurrentUser userId, String loginName, String password) {
        this.userId = userId;
        this.loginName = loginName;
        this.password = password;
    }

    @Override
    public boolean matches(SignInRequest request) {
        return userId.equals(request.getUserId()) &&
                loginName.equals(request.getLoginName()) &&
                password.equals(request.getPassword());
    }

}
