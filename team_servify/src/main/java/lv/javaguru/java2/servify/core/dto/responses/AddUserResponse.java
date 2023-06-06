package lv.javaguru.java2.servify.core.dto.responses;

import lv.javaguru.java2.servify.core.domain.UserEntity;
import java.util.List;

public class AddUserResponse extends CoreResponse {

    private UserEntity newUser;

    public AddUserResponse(List<CoreError> errors) {
        super(errors);
    }

    public AddUserResponse(UserEntity newUser) {
        this.newUser = newUser;
    }

    public UserEntity getNewUser() {
        return newUser;
    }
}
