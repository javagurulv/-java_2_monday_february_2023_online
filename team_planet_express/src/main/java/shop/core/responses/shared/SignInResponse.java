package shop.core.responses.shared;

import lombok.Getter;
import shop.core.dtos.UserDto;
import shop.core.responses.CoreError;
import shop.core.responses.CoreResponse;

import java.util.List;

@Getter
public class SignInResponse extends CoreResponse {

    private UserDto user;

    public SignInResponse(UserDto user) {
        this.user = user;
    }

    public SignInResponse(List<CoreError> errors) {
        super(errors);
    }

}
