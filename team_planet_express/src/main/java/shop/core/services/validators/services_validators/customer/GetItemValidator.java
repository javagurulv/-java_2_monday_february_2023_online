package shop.core.services.validators.services_validators.customer;

import org.springframework.stereotype.Component;
import shop.core_api.requests.customer.GetItemRequest;
import shop.core_api.responses.CoreError;

import java.util.ArrayList;
import java.util.List;

@Component
public class GetItemValidator {
    public List<CoreError> validate(GetItemRequest request) {
        List<CoreError> errors = new ArrayList<>();
        return errors;
    }
}
