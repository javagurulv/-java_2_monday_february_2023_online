package shop.acceptance_tests.custom;

import org.springframework.beans.factory.annotation.Autowired;
import shop.acceptance_tests.custom.tester.*;
import shop.core.services.actions.shared.SecurityService;
import shop.core.services.user.UserService;

public abstract class CustomAcceptanceTest {

    @Autowired
    protected AddItemToCartTester addItemToCartTester;
    @Autowired
    protected ListShopItemsTester listShopItemsTester;
    @Autowired
    protected RemoveItemFromCartTester removeItemFromCartTester;
    @Autowired
    protected ListCartItemsTester listCartItemsTester;
    @Autowired
    protected BuyTester buyCartTester;
    @Autowired
    private UserService userService;
    @Autowired
    private SecurityService securityService;

    protected void setupDefaultUser() {
    }

}
