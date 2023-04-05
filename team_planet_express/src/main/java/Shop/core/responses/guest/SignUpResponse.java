package Shop.core.responses.guest;

import Shop.core.domain.user.User;
import Shop.core.responses.CoreError;
import Shop.core.responses.CoreResponse;

import java.util.List;

public class SignUpResponse extends CoreResponse {

    private User user;

    public SignUpResponse(User user) {
        this.user = user;
    }

    public SignUpResponse(List<CoreError> errors) {
        super(errors);
    }

    public User getUser() {
        return user;
    }

}
