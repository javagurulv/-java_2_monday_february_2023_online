package Shop.core.services.actions.guest;

import Shop.core.services.user.UserRecord;
import Shop.core.services.user.UserService;
import Shop.core.domain.user.User;
import Shop.core.domain.user.UserRole;
import Shop.core.requests.guest.SignUpRequest;
import Shop.core.responses.CoreError;
import Shop.core.responses.guest.SignUpResponse;
import Shop.core.services.validators.actions.guest.SignUpValidator;

import java.util.List;

public class SignUpService {

    private final SignUpValidator validator;
    private final UserService userService;

    public SignUpService(SignUpValidator validator, UserService userService) {
        this.validator = validator;
        this.userService = userService;
    }

    public SignUpResponse execute(SignUpRequest request) {
        List<CoreError> errors = validator.validate(request);
        if (!errors.isEmpty()) {
            return new SignUpResponse(errors);
        }
        String name = request.getName();
        String loginName = request.getLoginName();
        String password = request.getPassword();
        UserRecord userRecord = new UserRecord(name, loginName, password, UserRole.CUSTOMER);
        User createdUser = userService.createUser(userRecord);
        request.getUserId().setValue(createdUser.getId());
        return new SignUpResponse(createdUser);
    }

}
