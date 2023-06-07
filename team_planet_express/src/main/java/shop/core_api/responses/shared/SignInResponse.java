package shop.core_api.responses.shared;

import lombok.Getter;
import shop.core.domain.user.User;
import shop.core_api.responses.CoreError;
import shop.core_api.responses.CoreResponse;

import java.util.List;

@Getter
public class SignInResponse extends CoreResponse {

    private User user;

    public SignInResponse(User user) {
        this.user = user;
    }

    public SignInResponse(List<CoreError> errors) {
        super(errors);
    }

}
