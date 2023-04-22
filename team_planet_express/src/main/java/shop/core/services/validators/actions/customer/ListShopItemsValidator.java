package shop.core.services.validators.actions.customer;

import shop.core.requests.customer.ListShopItemsRequest;
import shop.core.responses.CoreError;
import shop.core.services.validators.universal.system.CurrentUserIdValidator;
import shop.dependency_injection.DIDependency;

import java.util.ArrayList;
import java.util.List;

public class ListShopItemsValidator {

    @DIDependency
    private CurrentUserIdValidator userIdValidator;

    public List<CoreError> validate(ListShopItemsRequest request) {
        userIdValidator.validateCurrentUserIdIsPresent(request.getUserId());
        List<CoreError> errors = new ArrayList<>();
        return errors;
    }

}
