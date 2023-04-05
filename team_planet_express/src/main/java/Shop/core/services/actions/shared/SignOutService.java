package Shop.core.services.actions.shared;

import Shop.core.domain.user.User;
import Shop.core.domain.user.UserRole;
import Shop.core.requests.shared.SignOutRequest;
import Shop.core.responses.CoreError;
import Shop.core.responses.shared.SignOutResponse;
import Shop.core.services.user.UserRecord;
import Shop.core.services.user.UserService;
import Shop.core.services.validators.actions.shared.SignOutValidator;

import java.util.List;

public class SignOutService {

    public static final String BLANK = "";

    private final SignOutValidator validator;
    private final UserService userService;

    public SignOutService(SignOutValidator validator, UserService userService) {
        this.validator = validator;
        this.userService = userService;
    }

    public SignOutResponse execute(SignOutRequest request) {
        List<CoreError> errors = validator.validate(request);
        if (!errors.isEmpty()) {
            return new SignOutResponse(errors);
        }
        UserRecord userRecord = new UserRecord(UserRole.GUEST.getDefaultName(), BLANK, BLANK, UserRole.GUEST);
        User newUser = userService.findGuestWithOpenCart().orElseGet(
                () -> userService.createUser(userRecord));
        request.getUserId().setValue(newUser.getId());
        return new SignOutResponse();
    }

}
