package shop.core.services.actions.customer;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;
import shop.core.database.Repository;
import shop.core.domain.cart.Cart;
import shop.core.domain.cart.CartStatus;
import shop.core.requests.customer.BuyRequest;
import shop.core.responses.CoreError;
import shop.core.responses.customer.BuyResponse;
import shop.core.services.validators.actions.customer.BuyValidator;
import shop.core.services.validators.universal.system.DatabaseAccessValidator;

import java.util.List;

@Component
@Transactional
public class BuyService {

    @Autowired
    private Repository repository;
    @Autowired
    private BuyValidator validator;
    @Autowired
    private DatabaseAccessValidator databaseAccessValidator;


    public BuyResponse execute(BuyRequest request) {
        List<CoreError> errors = validator.validate(request);
        if (!errors.isEmpty()) {
            return new BuyResponse(errors);
        }
        Cart cart = databaseAccessValidator.getOpenCartByUserId(request.getCurrentUserId().getValue());
        repository.accessCartRepository().changeCartStatus(cart.getId(), CartStatus.CLOSED);
        return new BuyResponse();
    }

}
