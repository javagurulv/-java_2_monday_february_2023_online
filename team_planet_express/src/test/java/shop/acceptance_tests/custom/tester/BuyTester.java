package shop.acceptance_tests.custom.tester;

import org.springframework.context.ApplicationContext;
import shop.core.database.Repository;
import shop.core.domain.cart.Cart;
import shop.core.requests.customer.BuyRequest;
import shop.core.services.actions.customer.BuyService;
import shop.core.support.CurrentUser;

import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertTrue;

public class BuyTester extends Tester {

    public BuyTester(ApplicationContext applicationContext) {
        super(applicationContext);
    }

    public BuyTester buy() {
        BuyService buyService = applicationContext.getBean(BuyService.class);
        BuyRequest buyRequest = new BuyRequest(applicationContext.getBean(CurrentUser.class));
        buyService.execute(buyRequest);
        return this;
    }

    public BuyTester checkCartIsClosed() {
        Repository repository = applicationContext.getBean(Repository.class);
        CurrentUser currentUser = applicationContext.getBean(CurrentUser.class);
        Optional<Cart> cart = repository.accessCartDatabase().findOpenCartForUserId(currentUser.getUser());
        assertTrue(cart.isEmpty());
        return this;
    }

}
