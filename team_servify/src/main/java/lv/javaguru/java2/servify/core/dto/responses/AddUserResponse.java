package lv.javaguru.java2.servify.core.dto.responses;

import lv.javaguru.java2.servify.core.domain.UserEntity;
import lv.javaguru.java2.servify.core.dto.UserDTO;

import java.util.List;

public class AddUserResponse extends CoreResponse {

    private UserDTO newUser;
    private boolean isAdded;

    public AddUserResponse(List<CoreError> errors) {
        super(errors);
    }


    public AddUserResponse(UserDTO newUser) {
        this.newUser = newUser;
    }
    public AddUserResponse(boolean isAdded) {
        this.isAdded = isAdded;
    }

    public UserDTO getNewUser() {
        return newUser;
    }
}
