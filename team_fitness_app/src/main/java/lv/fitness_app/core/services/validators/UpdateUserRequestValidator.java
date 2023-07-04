package lv.fitness_app.core.services.validators;

import lv.fitness_app.core.requests.UpdateUserRequest;
import lv.fitness_app.core.responses.CoreError;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Component
public class UpdateUserRequestValidator {

    public List<CoreError> validate(UpdateUserRequest request) {
        List<CoreError> errors = new ArrayList<>();
        validateUsername(request).ifPresent(errors::add);
        validatePassword(request).ifPresent(errors::add);
        return errors;
    }

    private Optional<CoreError> validateUsername(UpdateUserRequest request) {
        return (request.getNewUsername() == null || request.getNewUsername().isEmpty())
                ? Optional.of(new CoreError("newUsername", "Must not be empty!"))
                : Optional.empty();
    }

    private Optional<CoreError> validatePassword(UpdateUserRequest request) {
        return (request.getNewPassword() == null || request.getNewPassword().isEmpty())
                ? Optional.of(new CoreError("newPassword", "Must not be empty!"))
                : Optional.empty();
    }
}
