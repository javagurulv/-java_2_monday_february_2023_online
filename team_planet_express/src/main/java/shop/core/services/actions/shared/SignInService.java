package shop.core.services.actions.shared;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;
import shop.core.converters.UserConverter;
import shop.core.domain.User;
import shop.core.dtos.UserDto;
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
    @Autowired
    private UserConverter userConverter;

    public SignInResponse execute(SignInRequest request) {
        List<CoreError> errors = validator.validate(request);
        if (!errors.isEmpty()) {
            return new SignInResponse(errors);
        }
        User newUser = databaseAccessValidator.getUserByLoginName(request.getLoginName());
        request.getCurrentUserId().setValue(newUser.getId());
        UserDto newUserDto = userConverter.toUserDto(newUser);
        return new SignInResponse(newUserDto);
    }

}
