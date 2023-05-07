package shop.core.responses.guest;

import lombok.Getter;
import shop.core.domain.user.User;
import shop.core.responses.CoreError;
import shop.core.responses.CoreResponse;

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
