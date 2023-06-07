package shop.core.services.validators.actions.customer;

import org.springframework.beans.factory.annotation.Autowired;
import shop.core.domain.user.User;
import shop.core.services.actions.shared.SecurityServiceImpl;
import shop.core_api.requests.customer.ListShopItemsRequest;
import shop.core_api.responses.CoreError;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

public class ListShopItemsValidator {

    @Autowired
    private SecurityServiceImpl securityService;

    public List<CoreError> validate(ListShopItemsRequest request) {
        List<CoreError> errors = new ArrayList<>();
        Optional<User> user = securityService.getAuthenticatedUserFromDB();
        if(user.isEmpty())
            errors.add(new CoreError("","", ""));
        return errors;
    }

}
