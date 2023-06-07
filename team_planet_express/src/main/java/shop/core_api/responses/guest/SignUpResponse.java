package shop.core_api.responses.guest;

import lombok.Getter;
import shop.core.domain.user.User;
import shop.core_api.responses.CoreError;
import shop.core_api.responses.CoreResponse;

import java.util.List;

@Getter
public class SignUpResponse extends CoreResponse {

    private User user;

    public SignUpResponse(User user) {
        this.user = user;
    }

    public SignUpResponse(List<CoreError> errors) {
        super(errors);
    }

}
