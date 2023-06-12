package shop.core.services.actions.customer;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import shop.core.database.CartRepository;
import shop.core.domain.cart.Cart;
import shop.core.domain.cart.CartStatus;
import shop.core.domain.user.User;
import shop.core.services.actions.shared.SecurityServiceImpl;
import shop.core.services.validators.actions.customer.BuyValidator;
import shop.core.services.validators.universal.system.DatabaseAccessProvider;
import shop.core_api.entry_point.customer.BuyService;
import shop.core_api.requests.customer.BuyRequest;
import shop.core_api.responses.CoreError;
import shop.core_api.responses.customer.BuyResponse;

import java.util.List;

@Service
@Transactional
public class BuyServiceImpl implements BuyService {

    @Autowired
    private CartRepository cartRepository;
    @Autowired
    private BuyValidator validator;
    @Autowired
    private DatabaseAccessProvider databaseAccessProvider;
    @Autowired
    private SecurityServiceImpl securityService;


    public BuyResponse execute(BuyRequest request) {
        List<CoreError> errors = validator.validate(request);
        if (!errors.isEmpty()) {
            return new BuyResponse(errors);
        }
        User user = securityService.getAuthenticatedUserFromDB().get();
        Cart cart = databaseAccessProvider.getOpenCartByUserId(user.getId());
        cartRepository.changeCartStatus(cart.getId(), CartStatus.CLOSED);
        cartRepository.save(new Cart(user));
        return new BuyResponse();
    }

}
