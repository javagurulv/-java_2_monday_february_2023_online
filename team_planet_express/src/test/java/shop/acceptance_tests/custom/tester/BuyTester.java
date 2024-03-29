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
    private BuyService buyService;

    public BuyTester buy() {
        BuyRequest buyRequest = new BuyRequest(currentUserId);
        buyService.execute(buyRequest);
        return this;
    }

    @SuppressWarnings("UnusedReturnValue")
    public BuyTester checkCartIsClosed() {
        Optional<Cart> cart = repository.accessCartRepository().findOpenCartForUserId(currentUserId.getValue());
        assertTrue(cart.isEmpty());
        return this;
    }

}
