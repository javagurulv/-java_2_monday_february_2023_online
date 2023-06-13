package shop.core.responses.guest;

import lombok.Getter;
import shop.core.dtos.UserDto;
import shop.core.responses.CoreError;
import shop.core.responses.CoreResponse;

import java.util.List;

@Getter
public class SignUpResponse extends CoreResponse {

    private UserDto user;

    public SignUpResponse(UserDto user) {
        this.user = user;
    }

    public SignUpResponse(List<CoreError> errors) {
        super(errors);
    }

}
