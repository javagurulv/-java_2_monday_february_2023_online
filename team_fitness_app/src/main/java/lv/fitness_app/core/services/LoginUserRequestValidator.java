package lv.fitness_app.core.services;

import lv.fitness_app.core.responses.CoreError;
import lv.fitness_app.core.requests.LoginUserRequest;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Component
public class LoginUserRequestValidator {

    public List<CoreError> validate(LoginUserRequest request) {
        List<CoreError> errors = new ArrayList<>();
        validateEmail(request).ifPresent(errors::add);
        validatePassword(request).ifPresent(errors::add);
        return errors;
    }

    private Optional<CoreError> validateEmail(LoginUserRequest request) {
        return (request.getPassword() == null || request.getPassword().isBlank())
                ? Optional.of(new CoreError("email", "Must not be empty!"))
                : Optional.empty();
    }

    private Optional<CoreError> validatePassword(LoginUserRequest request) {
        return (request.getPassword() == null || request.getPassword().isBlank())
                ? Optional.of(new CoreError("password", "Must not be empty!"))
                : Optional.empty();
    }

}
