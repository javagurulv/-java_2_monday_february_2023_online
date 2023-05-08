package lv.javaguru.java2.servify.core.validators;

import lv.javaguru.java2.servify.core.requests.user.SetUserNotActiveRequest;
import lv.javaguru.java2.servify.core.responses.CoreError;
import lv.javaguru.java2.servify.domain.FieldTitle;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Component
public class SetUserNotActiveValidator {

    public List<CoreError> validate(SetUserNotActiveRequest request) {
        List<CoreError> errors = new ArrayList<>();
        validateId(request).ifPresent(errors::add);
        return errors;
    }

    private Optional<CoreError> validateId(SetUserNotActiveRequest request) {
        return (request.getUserIdToSetInactive() == null)
                ? Optional.of(new CoreError(FieldTitle.ID, "Must not be empty"))
                : Optional.empty();
    }

}
