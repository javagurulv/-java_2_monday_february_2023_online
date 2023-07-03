package lv.fitness_app.core.responses;

import lv.fitness_app.core.domain.User;

import java.util.List;

public class DeleteUserResponse extends CoreResponse {

    private User deletedUser;

    public DeleteUserResponse(List<CoreError> errors) {
        super(errors);
    }

    public DeleteUserResponse(User deletedUser) {
        this.deletedUser = deletedUser;
    }

    public User getDeletedUser() {
        return deletedUser;
    }
}
