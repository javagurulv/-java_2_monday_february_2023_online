package shop.acceptance_tests.custom.tester;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import shop.core.domain.cart.Cart;
import shop.core.requests.customer.BuyRequest;
import shop.core.services.actions.customer.BuyService;

import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertTrue;

@Component
public class BuyTester extends Tester {
    @Autowired
    BuyService buyService;

    public BuyTester buy() {
        BuyRequest buyRequest = new BuyRequest(currentUserId);
        buyService.execute(buyRequest);
        return this;
    }

    public BuyTester checkCartIsClosed() {
        Optional<Cart> cart = cartDatabase.findOpenCartForUserId(currentUserId.getValue());
        assertTrue(cart.isEmpty());
        return this;
    }

}
