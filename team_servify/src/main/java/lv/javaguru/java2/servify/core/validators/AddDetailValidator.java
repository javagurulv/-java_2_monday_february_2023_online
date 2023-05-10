package lv.javaguru.java2.servify.core.validators;

import lv.javaguru.java2.servify.core.requests.detail.AddDetailRequest;
import lv.javaguru.java2.servify.core.responses.CoreError;
import lv.javaguru.java2.servify.domain.FieldTitle;
import org.springframework.stereotype.Service;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class AddDetailValidator {

    public List<CoreError> validate(AddDetailRequest request) {
        List<CoreError> errors = new ArrayList<>();
        validateId(request).ifPresent(errors::add);
        return errors;
    }

    private Optional<CoreError> validateId(AddDetailRequest request) {
        return (request.getId() == null || request.getId() < 1 || request.getId() > 15)
                ? Optional.of(new CoreError(FieldTitle.ID, "Not valid ID"))
                : Optional.empty();
    }

}
