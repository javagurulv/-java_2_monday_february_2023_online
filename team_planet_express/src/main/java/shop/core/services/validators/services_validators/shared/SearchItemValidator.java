package shop.core.services.validators.services_validators.shared;

import org.springframework.stereotype.Component;
import shop.core_api.requests.shared.SearchItemRequest;
import shop.core_api.responses.CoreError;

import java.util.ArrayList;
import java.util.List;

@Component
public class SearchItemValidator {

    private static final String FIELD_PRICE = "price";
    private static final String VALUE_NAME_PRICE = "Price";


    public List<CoreError> validate(SearchItemRequest request) {
        List<CoreError> errors = new ArrayList<>();

        return errors;
    }

}
