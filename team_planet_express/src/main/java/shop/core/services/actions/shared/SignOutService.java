package shop.core.services.actions.shared;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import shop.core.domain.user.User;
import shop.core.services.user.UserService;
import shop.core.services.validators.services_validators.shared.SignOutValidator;
import shop.core_api.requests.shared.SignOutRequest;
import shop.core_api.responses.CoreError;
import shop.core_api.responses.shared.SignOutResponse;

import java.util.List;

@Service
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
        User newUser = userService.findGuestWithOpenCart().orElseGet(
                () -> userService.createUser());
        request.getCurrentUserId().setValue(newUser.getId());
        return new SignOutResponse();
    }

}
