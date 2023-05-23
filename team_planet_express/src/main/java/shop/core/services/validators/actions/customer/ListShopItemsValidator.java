package shop.core.services.validators.actions.customer;

import org.springframework.beans.factory.annotation.Autowired;
import shop.core.requests.customer.ListShopItemsRequest;
import shop.core.responses.CoreError;
import shop.core.services.validators.universal.system.CurrentUserIdValidator;

import java.util.ArrayList;
import java.util.List;

public class ListShopItemsValidator {

    @Autowired
    private CurrentUserIdValidator userIdValidator;

    public List<CoreError> validate(ListShopItemsRequest request) {
        userIdValidator.validateCurrentUserIdIsPresent(request.getCurrentUser());
        List<CoreError> errors = new ArrayList<>();
        return errors;
    }

}
