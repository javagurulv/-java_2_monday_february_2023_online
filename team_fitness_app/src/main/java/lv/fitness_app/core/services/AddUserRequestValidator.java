package lv.fitness_app.core.services;

import lv.fitness_app.core.requests.AddUserRequest;
import lv.fitness_app.core.responses.CoreError;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Component
public class AddUserRequestValidator {
    public List<CoreError> validate(AddUserRequest request) {
        List<CoreError> errors = new ArrayList<>();
        validateEmail(request).ifPresent(errors::add);
        validateUsername(request).ifPresent(errors::add);
        validatePassword(request).ifPresent(errors::add);
        return errors;
    }

    private Optional<CoreError> validateUsername(AddUserRequest request) {
        return (request.getUsername() == null || request.getUsername().isBlank())
                ? Optional.of(new CoreError("username", "Must not be empty!"))
                : Optional.empty();
    }

    private Optional<CoreError> validatePassword(AddUserRequest request) {
        return (request.getPassword() == null || request.getPassword().isBlank())
                ? Optional.of(new CoreError("password", "Must not be empty!"))
                : Optional.empty();
    }
    private Optional<CoreError> validateEmail(AddUserRequest request) {
        return (request.getPassword() == null || request.getPassword().isBlank())
                ? Optional.of(new CoreError("email", "Must not be empty!"))
                : Optional.empty();
    }
}
