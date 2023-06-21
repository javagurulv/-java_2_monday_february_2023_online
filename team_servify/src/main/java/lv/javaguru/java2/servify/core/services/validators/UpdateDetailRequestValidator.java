package lv.javaguru.java2.servify.core.services.validators;

import lv.javaguru.java2.servify.core.domain.FieldTitle;
import lv.javaguru.java2.servify.core.dto.requests.UpdateDetailRequest;
import lv.javaguru.java2.servify.core.dto.responses.CoreError;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class UpdateDetailRequestValidator {

    public List<CoreError> validate(UpdateDetailRequest request) {
        List<CoreError> errors = new ArrayList<>();
//        validateType(request).ifPresent(errors::add);
//        validateSide(request).ifPresent(errors::add);
        validatePrice(request).ifPresent(errors::add);
        return errors;
    }

//    private Optional<CoreError> validateType(UpdateDetailRequest request) {
//        return (request.getType() == null || request.getType().isEmpty())
//                ? Optional.of(new CoreError(FieldTitle.DETAIL_TYPE, "Must not be Empty!"))
//                : Optional.empty();
//    }
//
//    private Optional<CoreError> validateSide(UpdateDetailRequest request) {
//        return (request.getSide() == null || request.getSide().isEmpty())
//                ? Optional.of(new CoreError(FieldTitle.DETAIL_SIDE, "Must not be Empty!"))
//                : Optional.empty();
//    }

    private Optional<CoreError> validatePrice(UpdateDetailRequest request) {
        return (request.getPrice().compareTo(BigDecimal.ZERO) <= 0)
                ? Optional.of(new CoreError(FieldTitle.DETAIL_PRICE, "Must not be 0 or less"))
                : Optional.empty();
    }

}
