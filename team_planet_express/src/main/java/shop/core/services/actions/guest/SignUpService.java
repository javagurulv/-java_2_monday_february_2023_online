package shop.core.services.actions.guest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;
import shop.core.converters.UserConverter;
import shop.core.domain.User;
import shop.core.dtos.UserDto;
import shop.core.enums.UserRole;
import shop.core.requests.guest.SignUpRequest;
import shop.core.responses.CoreError;
import shop.core.responses.guest.SignUpResponse;
import shop.core.services.user.UserCreationData;
import shop.core.services.user.UserService;
import shop.core.services.validators.actions.guest.SignUpValidator;

import java.util.List;

@Component
@Transactional
public class SignUpService {

    @Autowired
    private SignUpValidator validator;
    @Autowired
    private UserService userService;
    @Autowired
    private UserConverter userConverter;

    public SignUpResponse execute(SignUpRequest request) {
        List<CoreError> errors = validator.validate(request);
        if (!errors.isEmpty()) {
            return new SignUpResponse(errors);
        }
        UserCreationData userCreationData = new UserCreationData(
                request.getName(),
                request.getLoginName(),
                request.getPassword(),
                UserRole.CUSTOMER);
        User createdUser = userService.createUser(userCreationData);
        request.getCurrentUserId().setValue(createdUser.getId());
        UserDto userDto = userConverter.toUserDto(createdUser);
        return new SignUpResponse(userDto);
    }

}
