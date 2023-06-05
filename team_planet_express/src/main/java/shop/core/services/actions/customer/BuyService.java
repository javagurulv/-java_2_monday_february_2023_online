package shop.core.services.actions.customer;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;
import shop.core.database.CartRepository;
import shop.core.domain.cart.Cart;
import shop.core.domain.cart.CartStatus;
import shop.core.domain.user.User;
import shop.core.requests.customer.BuyRequest;
import shop.core.responses.CoreError;
import shop.core.responses.customer.BuyResponse;
import shop.core.services.actions.shared.SecurityService;
import shop.core.services.validators.actions.customer.BuyValidator;
import shop.core.services.validators.universal.system.DatabaseAccessValidator;

import java.util.List;

@Component
@Transactional
public class BuyService {

    @Autowired
    private CartRepository cartRepository;
    @Autowired
    private BuyValidator validator;
    @Autowired
    private DatabaseAccessValidator databaseAccessValidator;
    @Autowired
    private SecurityService securityService;


    public BuyResponse execute(BuyRequest request) {
        List<CoreError> errors = validator.validate(request);
        if (!errors.isEmpty()) {
            return new BuyResponse(errors);
        }
        User user = securityService.getAuthenticatedUserFromDB().get();
        Cart cart = databaseAccessValidator.getOpenCartByUserId(user.getId());
        cartRepository.changeCartStatus(cart.getId(), CartStatus.CLOSED);
        cartRepository.save(new Cart(user));
        return new BuyResponse();
    }

}
