package shop.matchers;

import org.mockito.ArgumentMatcher;
import shop.core.requests.shared.SignInRequest;
import shop.core.support.CurrentUserId;

public class SignInRequestMatcher implements ArgumentMatcher<SignInRequest> {

    private final CurrentUserId currentUserId;
    private final String loginName;
    private final String password;

    public SignInRequestMatcher(CurrentUserId currentUserId, String loginName, String password) {
        this.currentUserId = currentUserId;
        this.loginName = loginName;
        this.password = password;
    }

    @Override
    public boolean matches(SignInRequest request) {
        return currentUserId.equals(request.getCurrentUserId()) &&
                loginName.equals(request.getLoginName()) &&
                password.equals(request.getPassword());
    }

}
