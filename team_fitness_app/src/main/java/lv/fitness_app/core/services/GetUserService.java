package lv.fitness_app.core.services;

import lv.fitness_app.core.database.UserRepository;
import lv.fitness_app.core.requests.GetUserRequest;
import lv.fitness_app.core.responses.CoreError;
import lv.fitness_app.core.responses.GetUserResponse;
import lv.fitness_app.core.services.validators.GetUserValidator;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
@Component
@Transactional
public class GetUserService {

    @Autowired
    private UserRepository userRepository;
    @Autowired private GetUserValidator validator;

    public GetUserResponse execute(GetUserRequest request) {
        List<CoreError> errors = validator.validate(request);
        if (!errors.isEmpty()) {
            return new GetUserResponse(errors);
        }
        return userRepository.getByEmail(request.getEmail())
                .map(GetUserResponse::new)
                .orElseGet(() -> {
                    errors.add(new CoreError("email", "Not found!"));
                    return new GetUserResponse(errors);
                });
    }
}
