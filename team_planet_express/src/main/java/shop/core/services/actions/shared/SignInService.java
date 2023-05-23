package shop.core.services.actions.shared;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;
import shop.core.domain.user.User;
import shop.core.requests.shared.SignInRequest;
import shop.core.responses.CoreError;
import shop.core.responses.shared.SignInResponse;
import shop.core.services.validators.actions.shared.SignInValidator;
import shop.core.services.validators.universal.system.DatabaseAccessValidator;

import java.util.List;

@Component
@Transactional
public class SignInService {

    @Autowired
    private SignInValidator validator;
    @Autowired
    private DatabaseAccessValidator databaseAccessValidator;

    public SignInResponse execute(SignInRequest request) {
        List<CoreError> errors = validator.validate(request);
        if (!errors.isEmpty()) {
            return new SignInResponse(errors);
        }
        User newUser = databaseAccessValidator.getUserByLoginName(request.getLoginName());
        request.getCurrentUser().setUser(newUser);
        return new SignInResponse(newUser);
    }

}
