package shop.core.services.actions.shared;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import shop.core.domain.user.User;
import shop.core.services.validators.actions.shared.SignInValidator;
import shop.core.services.validators.universal.system.DatabaseAccessProvider;
import shop.core_api.requests.shared.SignInRequest;
import shop.core_api.responses.CoreError;
import shop.core_api.responses.shared.SignInResponse;

import java.util.List;

@Service
@Transactional
public class SignInService {

    @Autowired
    private SignInValidator validator;
    @Autowired
    private DatabaseAccessProvider databaseAccessProvider;

    public SignInResponse execute(SignInRequest request) {
        List<CoreError> errors = validator.validate(request);
        if (!errors.isEmpty()) {
            return new SignInResponse(errors);
        }
        User newUser = databaseAccessProvider.getUserByLoginName(request.getLoginName());
        request.getCurrentUserId().setValue(newUser.getId());
        return new SignInResponse(newUser);
    }

}
