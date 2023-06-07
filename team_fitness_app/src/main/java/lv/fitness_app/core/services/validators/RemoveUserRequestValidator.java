package lv.fitness_app.core.services.validators;

import lv.fitness_app.core.requests.RemoveUserRequest;
import lv.fitness_app.core.responses.CoreError;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Component
public class RemoveUserRequestValidator {


    public List<CoreError> validate(RemoveUserRequest request) {
        List<CoreError> errors = new ArrayList<>();
        validateEmail(request).ifPresent(errors::add);
        validatePassword(request).ifPresent(errors::add);
        return errors;
    }

    private Optional<CoreError> validateEmail(RemoveUserRequest request) {
        return (request.getEmail() == null || request.getEmail().toString().isBlank())
                ? Optional.of(new CoreError("email", "Must not be empty!"))
                : Optional.empty();
    }

    private Optional<CoreError> validatePassword(RemoveUserRequest request) {
        return (request.getPassword() == null || request.getPassword().isBlank())
                ? Optional.of(new CoreError("password", "Must not be empty!"))
                : Optional.empty();
    }
}
