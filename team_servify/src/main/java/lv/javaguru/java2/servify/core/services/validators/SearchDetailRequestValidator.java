package lv.javaguru.java2.servify.core.services.validators;

import lv.javaguru.java2.servify.core.domain.FieldTitle;
import lv.javaguru.java2.servify.core.requests.SearchDetailRequest;
import lv.javaguru.java2.servify.core.responses.CoreError;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
@Service
public class SearchDetailRequestValidator {

    public List<CoreError> validate(SearchDetailRequest request) {
        List<CoreError> errors = new ArrayList<>();
        errors.addAll(validateSearchFieldRequest(request));
        return errors;
    }

    public List<CoreError> validateSearchFieldRequest(SearchDetailRequest request) {
        List<CoreError> errors = new ArrayList<>();
        if (isEmpty(request.getDetailType()) && isEmpty(request.getDetailSide())) {
            errors.add(new CoreError(FieldTitle.DETAIL_TYPE, "Must not be empty!"));
            errors.add(new CoreError(FieldTitle.DETAIL_SIDE, "Must not be empty!"));
        }
        return errors;
    }

    private boolean isEmpty(String str) {
        return str == null || str.isEmpty();
    }
}
