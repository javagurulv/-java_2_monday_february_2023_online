package shop.core.services.actions.guest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import shop.core.domain.user.User;
import shop.core.domain.user.UserRole;
import shop.core.requests.guest.SignUpRequest;
import shop.core.responses.CoreError;
import shop.core.responses.guest.SignUpResponse;
import shop.core.services.user.UserCreationData;
import shop.core.services.user.UserService;
import shop.core.services.validators.actions.guest.SignUpValidator;

import java.util.List;

@Component
public class SignUpService {

    @Autowired
    private SignUpValidator validator;
    @Autowired
    private UserService userService;

    public SignUpResponse execute(SignUpRequest request) {
        List<CoreError> errors = validator.validate(request);
        if (!errors.isEmpty()) {
            return new SignUpResponse(errors);
        }
        String name = request.getName();
        String loginName = request.getLoginName();
        String password = request.getPassword();
        UserCreationData userCreationData = new UserCreationData(name, loginName, password, UserRole.CUSTOMER);
        User createdUser = userService.createUser(userCreationData);
        request.getUserId().setValue(createdUser.getId());
        return new SignUpResponse(createdUser);
    }

}
