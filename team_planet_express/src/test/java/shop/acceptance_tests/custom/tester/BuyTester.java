package shop.acceptance_tests.custom.tester;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;
import shop.core.database.specifications.CartSpecs;
import shop.core.domain.cart.Cart;
import shop.core.services.actions.customer.BuyServiceImpl;
import shop.core_api.dto.cart.CartDTO;
import shop.core_api.requests.customer.BuyRequest;

import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertFalse;

@Component
@Transactional
public class BuyTester extends Tester {

    @Autowired
    private BuyServiceImpl buyService;

    public BuyTester buy(CartDTO cartDTO) {
        BuyRequest buyRequest = new BuyRequest(cartDTO);
        buyService.execute(buyRequest);
        return this;
    }

    public BuyTester checkNewCartIsOpen() {
        Optional<Cart> cart = cartRepository.findOne(CartSpecs.findOpenCartForUser(securityService.getAuthenticatedUserFromDB().get()));
        assertFalse(cart.isEmpty());
        return this;
    }

}
