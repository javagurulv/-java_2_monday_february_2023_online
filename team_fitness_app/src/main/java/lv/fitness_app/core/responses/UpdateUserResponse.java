package lv.fitness_app.core.responses;

import lv.fitness_app.core.domain.User;

import java.util.List;

public class UpdateUserResponse extends CoreResponse {

    private User updatedUser;

    public UpdateUserResponse(List<CoreError> errors) {
        super(errors);
    }

    public UpdateUserResponse(User updatedUser) {
        this.updatedUser = updatedUser;
    }

    public User getUpdatedUser() {
        return updatedUser;
    }


}
