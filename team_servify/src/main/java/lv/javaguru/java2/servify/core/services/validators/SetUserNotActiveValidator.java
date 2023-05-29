package lv.javaguru.java2.servify.core.services.validators;

import lv.javaguru.java2.servify.core.domain.FieldTitle;
import lv.javaguru.java2.servify.core.dto.requests.SetUserNotActiveRequest;
import lv.javaguru.java2.servify.core.dto.responses.CoreError;
import org.springframework.stereotype.Service;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
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