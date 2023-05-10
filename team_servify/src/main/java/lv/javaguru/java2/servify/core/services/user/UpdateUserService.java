package lv.javaguru.java2.servify.core.services.user;

import lv.javaguru.java2.servify.core.database.UsersDatabase;
import lv.javaguru.java2.servify.core.requests.user.UpdateUserRequest;
import lv.javaguru.java2.servify.core.responses.CoreError;
import lv.javaguru.java2.servify.core.responses.user.UpdateUserResponse;
import lv.javaguru.java2.servify.core.validators.UpdateUserValidator;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.util.List;

@Service
public class UpdateUserService {
    @Autowired
    private UsersDatabase userDB;
    @Autowired
    private UpdateUserValidator validator;

    public UpdateUserResponse execute(UpdateUserRequest request) {
        List<CoreError> errors = validator.validate(request);
        if(!errors.isEmpty()) {
            return new UpdateUserResponse(errors);
        }
        return null;
    }
}
