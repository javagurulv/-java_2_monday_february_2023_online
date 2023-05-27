package lv.javaguru.java2.servify.core.services.users;

import lv.javaguru.java2.servify.core.database.UserRepository;
import lv.javaguru.java2.servify.core.dto.responses.SetUserNotActiveResponse;
import lv.javaguru.java2.servify.core.services.validators.SetUserNotActiveValidator;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.util.List;

@Service
public class SetUserNotActiveService {
    @Autowired private UserRepository userDB;
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
