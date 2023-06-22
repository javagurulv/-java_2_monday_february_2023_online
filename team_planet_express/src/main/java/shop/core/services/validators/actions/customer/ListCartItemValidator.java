package shop.core.services.validators.actions.customer;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import shop.core.requests.customer.ListCartItemsRequest;
import shop.core.responses.CoreError;
import shop.core.services.validators.cart.CartValidator;
import shop.core.services.validators.universal.system.CurrentUserIdValidator;
import shop.core.services.validators.universal.system.RepositoryAccessValidator;

import java.util.ArrayList;
import java.util.List;

@Component
public class ListCartItemValidator {

    @Autowired
    private CurrentUserIdValidator userIdValidator;
    @Autowired
    private CartValidator cartValidator;
    @Autowired
    private RepositoryAccessValidator repositoryAccessValidator;


    public List<CoreError> validate(ListCartItemsRequest request) {
        userIdValidator.validateCurrentUserIdIsPresent(request.getCurrentUserId());
        List<CoreError> errors = new ArrayList<>();
        cartValidator.validateOpenCartExistsForUser(
                repositoryAccessValidator.getUserById(request.getCurrentUserId().getValue())).ifPresent(errors::add);
        return errors;
    }

}
