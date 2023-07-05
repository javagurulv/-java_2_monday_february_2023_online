package shop.core.services.validators.services_validators.shared;

import org.springframework.stereotype.Component;
import shop.core_api.requests.shared.SignOutRequest;
import shop.core_api.responses.CoreError;

import java.util.ArrayList;
import java.util.List;

@Component
public class SignOutValidator {

    public List<CoreError> validate(SignOutRequest request) {
        //userIdValidator.validateCurrentUserIdIsPresent(request.getCurrentUserId());
        List<CoreError> errors = new ArrayList<>();
        return errors;
    }

}
