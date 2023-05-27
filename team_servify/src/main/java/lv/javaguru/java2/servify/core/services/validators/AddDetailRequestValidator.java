package lv.javaguru.java2.servify.core.services.validators;

import lv.javaguru.java2.servify.core.dto.requests.AddDetailRequest;
import lv.javaguru.java2.servify.core.dto.responses.CoreError;
import lv.javaguru.java2.servify.core.domain.FieldTitle;
import org.springframework.stereotype.Service;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class AddDetailRequestValidator {

    public List<CoreError> validate(AddDetailRequest request) {
        List<CoreError> errors = new ArrayList<>();
        validateType(request).ifPresent(errors::add);
        validateSide(request).ifPresent(errors::add);
        return errors;
    }

    private Optional<CoreError> validateType(AddDetailRequest request) {
        return (request.getDetailType() == null || request.getDetailType().isEmpty())
                ? Optional.of(new CoreError(FieldTitle.DETAIL_TYPE, "Not valid Type"))
                : Optional.empty();
    }

    private Optional<CoreError> validateSide(AddDetailRequest request) {
        return (request.getDetailSide() == null || request.getDetailSide().isEmpty())
                ? Optional.of(new CoreError(FieldTitle.DETAIL_SIDE, "Not valid Side"))
                : Optional.empty();
    }

}
