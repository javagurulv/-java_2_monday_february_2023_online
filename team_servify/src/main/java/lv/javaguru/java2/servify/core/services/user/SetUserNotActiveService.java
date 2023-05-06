package lv.javaguru.java2.servify.core.services.user;

import lv.javaguru.java2.servify.core.database.UsersDatabase;
import lv.javaguru.java2.servify.core.requests.user.SetUserNotActiveRequest;
import lv.javaguru.java2.servify.core.responses.CoreError;
import lv.javaguru.java2.servify.core.responses.user.SetUserNotActiveResponse;
import lv.javaguru.java2.servify.core.validators.SetUserNotActiveValidator;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class SetUserNotActiveService {
    @Autowired private UsersDatabase userDB;
    @Autowired private SetUserNotActiveValidator validator;

    public SetUserNotActiveResponse execute(SetUserNotActiveRequest request) {
        List<CoreError> errors = validator.validate(request);
        if (!errors.isEmpty()) {
            return new SetUserNotActiveResponse(errors);
        }

        boolean isUserInactivated = userDB.deactivateUser(request.getUserIdToSetInactive());
        return new SetUserNotActiveResponse(isUserInactivated);
    }
}
