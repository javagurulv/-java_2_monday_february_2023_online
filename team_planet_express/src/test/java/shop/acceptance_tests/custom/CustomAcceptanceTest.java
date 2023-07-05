package shop.acceptance_tests.custom;

import org.springframework.beans.factory.annotation.Autowired;
import shop.acceptance_tests.custom.tester.*;

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


}
