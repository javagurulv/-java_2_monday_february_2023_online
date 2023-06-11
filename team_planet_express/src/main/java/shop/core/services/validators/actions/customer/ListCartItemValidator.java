package shop.core.services.validators.actions.customer;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import shop.core.domain.user.User;
import shop.core.services.actions.shared.SecurityServiceImpl;
import shop.core.services.validators.cart.CartValidator;
import shop.core_api.requests.customer.GetListCartItemsRequest;
import shop.core_api.responses.CoreError;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Component
public class ListCartItemValidator {

    @Autowired
    private CartValidator cartValidator;
    @Autowired
    private SecurityServiceImpl securityService;


    public List<CoreError> validate(GetListCartItemsRequest request) {
        List<CoreError> errors = new ArrayList<>();
        Optional<User> user = securityService.getAuthenticatedUserFromDB();
        if (user.isEmpty())
            errors.add(new CoreError("", "", ""));
        cartValidator.validateOpenCartExistsForUserId(user.get().getId()).ifPresent(errors::add);
        return errors;
    }

}
