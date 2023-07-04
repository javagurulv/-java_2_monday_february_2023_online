package lv.fitness_app.core.services.validators;

import lv.fitness_app.core.requests.GetUserRequest;
import lv.fitness_app.core.responses.CoreError;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

//@Component
public class GetUserValidator {

    public List<CoreError> validate(GetUserRequest request) {
        List<CoreError> errors = new ArrayList<>();
        validateEmail(request).ifPresent(errors::add);
        return errors;
    }

    private Optional<CoreError> validateEmail(GetUserRequest request) {
        return (request.getEmail() == null)
                ? Optional.of(new CoreError("email", "Must not be empty!"))
                : Optional.empty();
    }
}
