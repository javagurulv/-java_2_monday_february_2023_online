package shop.acceptance_tests.custom.tester;

import org.springframework.context.ApplicationContext;
import shop.core.database.Database;
import shop.core.domain.cart.Cart;
import shop.core.requests.customer.BuyRequest;
import shop.core.services.actions.customer.BuyService;
import shop.core.support.CurrentUserId;

import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertTrue;

public class BuyTester extends Tester {

    public BuyTester(ApplicationContext applicationContext) {
        super(applicationContext);
    }

    public BuyTester buy() {
        BuyService buyService = applicationContext.getBean(BuyService.class);
        BuyRequest buyRequest = new BuyRequest(applicationContext.getBean(CurrentUserId.class));
        buyService.execute(buyRequest);
        return this;
    }

    public BuyTester checkCartIsClosed() {
        Database database = applicationContext.getBean(Database.class);
        CurrentUserId currentUserId = applicationContext.getBean(CurrentUserId.class);
        Optional<Cart> cart = database.accessCartDatabase().findOpenCartForUserId(currentUserId.getValue());
        assertTrue(cart.isEmpty());
        return this;
    }

}
