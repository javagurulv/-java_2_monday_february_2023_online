package lv.fitness_app.core.responses;

import lv.fitness_app.core.domain.User;

import java.util.List;

public class AddUserResponse extends CoreResponse {
    private User newUser;

    public AddUserResponse(List<CoreError> errors) {
        super(errors);
    }

    public AddUserResponse(User newUser) {
        this.newUser = newUser;
    }

    public User getNewUser() {
        return newUser;
    }

}
