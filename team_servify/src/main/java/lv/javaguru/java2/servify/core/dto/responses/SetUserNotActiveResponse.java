package lv.javaguru.java2.servify.core.dto.responses;

import java.util.List;

public class SetUserNotActiveResponse extends CoreResponse {

    private boolean userInactivated;

    public SetUserNotActiveResponse(List<CoreError> errors) {
        super(errors);
    }

    public SetUserNotActiveResponse(boolean userInactivated) {
        this.userInactivated = userInactivated;
    }

    public boolean isUserInactivated() {
        return userInactivated;
    }
}
