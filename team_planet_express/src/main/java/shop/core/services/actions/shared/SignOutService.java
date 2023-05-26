package shop.core.services.actions.shared;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;
import shop.core.domain.user.User;
import shop.core.domain.user.UserRole;
import shop.core.requests.shared.SignOutRequest;
import shop.core.responses.CoreError;
import shop.core.responses.shared.SignOutResponse;
import shop.core.services.user.UserCreationData;
import shop.core.services.user.UserService;
import shop.core.services.validators.actions.shared.SignOutValidator;

import java.util.List;

@Component
@Transactional
public class SignOutService {

    public static final String BLANK = "";

    @Autowired
    private SignOutValidator validator;
    @Autowired
    private UserService userService;

    public SignOutResponse execute(SignOutRequest request) {
        List<CoreError> errors = validator.validate(request);
        if (!errors.isEmpty()) {
            return new SignOutResponse(errors);
        }
        UserCreationData userCreationData = new UserCreationData(UserRole.GUEST.getDefaultName(), BLANK, BLANK, UserRole.GUEST);
        User newUser = userService.findGuestWithOpenCart().orElseGet(
                () -> userService.createUser(userCreationData));
        request.getCurrentUserId().setValue(newUser.getId());
        return new SignOutResponse();
    }

}
