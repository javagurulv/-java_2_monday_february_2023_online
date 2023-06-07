package lv.javaguru.java2.servify.core.services.validators;

import lv.javaguru.java2.servify.core.domain.FieldTitle;
import lv.javaguru.java2.servify.core.dto.requests.RemoveDetailRequest;
import lv.javaguru.java2.servify.core.dto.responses.CoreError;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
@Service
public class RemoveDetailRequestValidator {

    public List<CoreError> validate(RemoveDetailRequest request) {
        List<CoreError> errors = new ArrayList<>();
        validateDetailId(request).ifPresent(errors::add);
        return errors;
    }

    public Optional<CoreError> validateDetailId(RemoveDetailRequest request) {
        return (request.getDetailId()== null)
                ? Optional.of(new CoreError(FieldTitle.ID, "Must not be empty"))
                : Optional.empty();
    }
}
