package lv.javaguru.java2.servify.core.services.validators;

import lv.javaguru.java2.servify.core.domain.FieldTitle;
import lv.javaguru.java2.servify.core.dto.requests.GetDetailRequest;
import lv.javaguru.java2.servify.core.dto.responses.CoreError;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
@Service
public class GetDetailValidator {

    public List<CoreError> validate(GetDetailRequest request) {
        List<CoreError> errors = new ArrayList<>();
        validateId(request).ifPresent(errors::add);
        return errors;
    }
    private Optional<CoreError> validateId(GetDetailRequest request) {
        return (request.getId() == null)
                ? Optional.of(new CoreError(FieldTitle.ID, "Id must not be empty"))
                :Optional.empty();
    }
}
