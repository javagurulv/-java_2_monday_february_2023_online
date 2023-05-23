package shop.matchers;

import org.mockito.ArgumentMatcher;
import shop.core.requests.shared.SignInRequest;
import shop.core.support.CurrentUser;

public class SignInRequestMatcher implements ArgumentMatcher<SignInRequest> {

    private final CurrentUser currentUser;
    private final String loginName;
    private final String password;

    public SignInRequestMatcher(CurrentUser currentUser, String loginName, String password) {
        this.currentUser = currentUser;
        this.loginName = loginName;
        this.password = password;
    }

    @Override
    public boolean matches(SignInRequest request) {
        return currentUser.equals(request.getCurrentUser()) &&
                loginName.equals(request.getLoginName()) &&
                password.equals(request.getPassword());
    }

}
