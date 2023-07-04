package lv.fitness_app.core.services;

import lv.fitness_app.core.database.UserRepository;
import lv.fitness_app.core.database.jpa.JpaUserRepository;
import lv.fitness_app.core.requests.UpdateUserRequest;
import lv.fitness_app.core.responses.CoreError;
import lv.fitness_app.core.responses.UpdateUserResponse;
import lv.fitness_app.core.services.validators.UpdateUserRequestValidator;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Component
@Transactional
public class UpdateUserService {

    @Autowired
    private JpaUserRepository userRepository;
    @Autowired private UpdateUserRequestValidator validator;

    public UpdateUserResponse execute(UpdateUserRequest request) {
        List<CoreError> errors = validator.validate(request);
        if (!errors.isEmpty()) {
            return new UpdateUserResponse(errors);
        }

        return userRepository.findById(request.getEmail())
                .map(user -> {
                    user.setUsername(request.getNewUsername());
                    user.setPassword(request.getNewPassword());
                    return new UpdateUserResponse(user);
                })
                .orElseGet(() -> {
                    errors.add(new CoreError("email", "Not found!"));
                    return new UpdateUserResponse(errors);
                });
    }
}
