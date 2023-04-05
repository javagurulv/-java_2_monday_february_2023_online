package Shop.core.services.actions.shared;

import Shop.core.database.Database;
import Shop.core.domain.user.User;
import Shop.core.requests.shared.SignInRequest;
import Shop.core.responses.CoreError;
import Shop.core.responses.shared.SignInResponse;
import Shop.core.services.exception.ServiceMissingDataException;
import Shop.core.services.validators.actions.shared.SignInValidator;

import java.util.List;

public class SignInService {

    private final Database database;
    private final SignInValidator validator;

    public SignInService(Database database, SignInValidator validator) {
        this.database = database;
        this.validator = validator;
    }

    public SignInResponse execute(SignInRequest request) {
        List<CoreError> errors = validator.validate(request);
        if (!errors.isEmpty()) {
            return new SignInResponse(errors);
        }
        User newUser = getUserByLoginName(request.getLoginName());
        request.getUserId().setValue(newUser.getId());
        return new SignInResponse(newUser);
    }

    //TODO yeet, duplicate
    private User getUserByLoginName(String login) {
        return database.accessUserDatabase().findByLogin(login)
                .orElseThrow(ServiceMissingDataException::new);
    }

}
