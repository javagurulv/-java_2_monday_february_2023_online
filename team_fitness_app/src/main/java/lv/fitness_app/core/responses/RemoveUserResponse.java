package lv.fitness_app.core.responses;

import java.util.List;

public class RemoveUserResponse extends CoreResponse {

    private boolean userRemoved;

    public RemoveUserResponse(List<CoreError> errors) {
        super(errors);
    }

    public RemoveUserResponse(boolean userRemoved) {
        this.userRemoved = userRemoved;
    }

    public boolean isUserRemoved() {
        return userRemoved;
    }
}
