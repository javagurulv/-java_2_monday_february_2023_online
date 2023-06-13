package shop.core.services.validators.actions.customer;

import shop.core.requests.customer.ListShopItemsRequest;
import shop.core.responses.CoreError;

import java.util.ArrayList;
import java.util.List;

public class ListShopItemsValidator {

    public List<CoreError> validate(ListShopItemsRequest request) {
        List<CoreError> errors = new ArrayList<>();
        return errors;
    }

}
