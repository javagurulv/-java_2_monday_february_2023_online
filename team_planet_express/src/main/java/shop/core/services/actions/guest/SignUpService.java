package shop.core.services.actions.guest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import shop.core.domain.user.User;
import shop.core.services.validators.services_validators.guest.SignUpValidator;
import shop.core_api.requests.guest.SignUpRequest;
import shop.core_api.responses.CoreError;
import shop.core_api.responses.guest.SignUpResponse;

import java.util.List;

@Service
@Transactional
public class SignUpService {

    @Autowired
    private SignUpValidator validator;

    public SignUpResponse execute(SignUpRequest request) {
        List<CoreError> errors = validator.validate(request);
        if (!errors.isEmpty()) {
            return new SignUpResponse(errors);
        }
        String name = request.getName();
        String loginName = request.getLoginName();
        String password = request.getPassword();
        return new SignUpResponse(new User());
    }

}
