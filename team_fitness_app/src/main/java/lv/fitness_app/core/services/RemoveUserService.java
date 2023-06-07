package lv.fitness_app.core.services;

import lv.fitness_app.core.domain.User;
import lv.fitness_app.core.services.validators.RemoveUserRequestValidator;
import lv.fitness_app.database.UserRepository;
import lv.fitness_app.core.responses.CoreError;
import lv.fitness_app.core.responses.RemoveUserResponse;
import lv.fitness_app.core.requests.RemoveUserRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class RemoveUserService {

    @Autowired
    private UserRepository userRepository;
    @Autowired private RemoveUserRequestValidator validator;


    public RemoveUserResponse execute(RemoveUserRequest request) {
        List<CoreError> errors = validator.validate(request);
        boolean isUserRemoved = false;
        if (!errors.isEmpty()) {
            return new RemoveUserResponse(errors);
        } else if (userRepository.findUserByEmail(request.getEmail())!= null) {
            User user = userRepository.findUserByEmail(request.getEmail());
            if (user.getEmail().equals(request.getEmail()) && user.getPassword().equals(request.getPassword())) {
                userRepository.deleteUser(user);
                isUserRemoved = true;
            }
        }
        return new RemoveUserResponse(isUserRemoved);
    }
}