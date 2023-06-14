package lv.javaguru.java2.servify.core.services.validators;

import lv.javaguru.java2.servify.core.domain.FieldTitle;
import lv.javaguru.java2.servify.core.dto.requests.UpdateDetailRequest;
import lv.javaguru.java2.servify.core.dto.responses.CoreError;
import org.springframework.stereotype.Service;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class UpdateDetailRequestValidator {
    public List<CoreError> validate(UpdateDetailRequest request) {
        List<CoreError> errors = new ArrayList<>();
        validateType(request).ifPresent(errors::add);
        valitateSide(request).ifPresent(errors::add);
        //TODO validator for price BigDecimal
        return errors;
    }

    public Optional<CoreError> validateType(UpdateDetailRequest request) {
        return (request.getType() == null || request.getType().isEmpty())
                ? Optional.of(new CoreError(FieldTitle.DETAIL_TYPE, "Must not be Empty!"))
                : Optional.empty();
    }
    public Optional<CoreError> valitateSide(UpdateDetailRequest request) {
        return (request.getSide() == null || request.getSide().isEmpty())
                ? Optional.of(new CoreError(FieldTitle.DETAIL_SIDE, "Must not be Empty!"))
                : Optional.empty();
    }

}
