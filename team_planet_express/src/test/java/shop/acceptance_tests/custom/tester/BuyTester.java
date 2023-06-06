package shop.acceptance_tests.custom.tester;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import shop.core.domain.cart.Cart;
import shop.core.requests.customer.BuyRequest;
import shop.core.services.actions.customer.BuyService;

import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertFalse;

@Component
public class BuyTester extends Tester {

    @Autowired
    private BuyService buyService;

    public BuyTester buy() {
        BuyRequest buyRequest = new BuyRequest();
        buyService.execute(buyRequest);
        return this;
    }

    @SuppressWarnings("UnusedReturnValue")
    public BuyTester checkNewCartIsOpen() {
        Optional<Cart> cart = cartRepository.findOpenCartForUserId(securityService.getAuthenticatedUserFromDB().get().getId());
        assertFalse(cart.isEmpty());
        return this;
    }

}
