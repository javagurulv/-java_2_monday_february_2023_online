package shop.core.services.validators.actions.shared;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import shop.core.requests.shared.SignOutRequest;
import shop.core.responses.CoreError;
import shop.core.services.validators.universal.system.CurrentUserIdValidator;

import java.util.ArrayList;
import java.util.List;

@Component
public class SignOutValidator {

    @Autowired
    private CurrentUserIdValidator userIdValidator;

    public List<CoreError> validate(SignOutRequest request) {
        userIdValidator.validateCurrentUserIdIsPresent(request.getCurrentUserId());
        List<CoreError> errors = new ArrayList<>();
        return errors;
    }

}
