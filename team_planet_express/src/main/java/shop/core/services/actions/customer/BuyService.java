package shop.core.services.actions.customer;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;
import shop.core.database.jpa.JpaCartRepository;
import shop.core.domain.Cart;
import shop.core.enums.CartStatus;
import shop.core.requests.customer.BuyRequest;
import shop.core.responses.CoreError;
import shop.core.responses.customer.BuyResponse;
import shop.core.services.validators.actions.customer.BuyValidator;
import shop.core.services.validators.universal.system.RepositoryAccessValidator;

import java.util.List;

@Component
@Transactional
public class BuyService {

    @Autowired
    private JpaCartRepository cartRepository;
    @Autowired
    private BuyValidator validator;
    @Autowired
    private RepositoryAccessValidator repositoryAccessValidator;


    public BuyResponse execute(BuyRequest request) {
        List<CoreError> errors = validator.validate(request);
        if (!errors.isEmpty()) {
            return new BuyResponse(errors);
        }
        Cart cart = repositoryAccessValidator.getOpenCartByUser(
                repositoryAccessValidator.getUserById(request.getCurrentUserId().getValue()));
        cartRepository.updateCartStatus(cart.getId(), CartStatus.CLOSED);
        return new BuyResponse();
    }

}
