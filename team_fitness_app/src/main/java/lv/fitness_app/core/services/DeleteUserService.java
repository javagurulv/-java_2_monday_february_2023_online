package lv.fitness_app.core.services;

import lv.fitness_app.core.database.UserRepository;
import lv.fitness_app.core.database.jpa.JpaUserRepository;
import lv.fitness_app.core.requests.DeleteUserRequest;
import lv.fitness_app.core.responses.CoreError;
import lv.fitness_app.core.responses.DeleteUserResponse;
import lv.fitness_app.core.services.validators.DeleteUserValidator;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Component
@Transactional
public class DeleteUserService {

    @Autowired
    private JpaUserRepository userRepository;
    @Autowired private DeleteUserValidator validator;

    public DeleteUserResponse execute(DeleteUserRequest request) {
        List<CoreError> errors = validator.validate(request);
        if (!errors.isEmpty()) {
            return new DeleteUserResponse(errors);
        }
        return userRepository.findById(request.getEmail())
                .map(user -> {
                    userRepository.deleteById(request.getEmail());
                    return new DeleteUserResponse(user);
                })
                .orElseGet(() -> {
                    errors.add(new CoreError("email", "Not found!"));
                    return new DeleteUserResponse(errors);
                });
    }
}
