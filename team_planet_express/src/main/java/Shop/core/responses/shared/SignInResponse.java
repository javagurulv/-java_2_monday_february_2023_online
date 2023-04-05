package Shop.core.responses.shared;

import Shop.core.domain.user.User;
import Shop.core.responses.CoreError;
import Shop.core.responses.CoreResponse;

import java.util.List;

public class SignInResponse extends CoreResponse {

    private User user;

    public SignInResponse(User user) {
        this.user = user;
    }

    public SignInResponse(List<CoreError> errors) {
        super(errors);
    }

    public User getUser() {
        return user;
    }

}
