package shop.core.services.validators.actions.customer;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import shop.core.domain.user.User;
import shop.core.requests.customer.ListCartItemsRequest;
import shop.core.responses.CoreError;
import shop.core.services.validators.cart.CartValidator;
import shop.core.services.validators.universal.system.CurrentUserIdValidator;
import shop.core.services.validators.universal.system.DatabaseAccessValidator;

import java.util.ArrayList;
import java.util.List;

@Component
public class ListCartItemValidator {

    @Autowired
    private CurrentUserIdValidator userIdValidator;
    @Autowired
    private CartValidator cartValidator;
    @Autowired
    private DatabaseAccessValidator databaseAccessValidator;


    public List<CoreError> validate(ListCartItemsRequest request) {
        userIdValidator.validateCurrentUserIdIsPresent(request.getUserId());
        List<CoreError> errors = new ArrayList<>();
        cartValidator.validateOpenCartExistsForUserId(request.getUserId().getUser()).ifPresent(errors::add);
        return errors;
    }

}
