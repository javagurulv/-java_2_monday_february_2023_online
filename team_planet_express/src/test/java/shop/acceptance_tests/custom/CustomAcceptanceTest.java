package shop.acceptance_tests.custom;

import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.context.ApplicationContext;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.jdbc.Sql;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import shop.acceptance_tests.ApplicationContextSetup;
import shop.acceptance_tests.custom.tester.*;
import shop.config.ShopConfiguration;

@ExtendWith(SpringExtension.class)
@ContextConfiguration(classes = {ShopConfiguration.class})
@Sql("/testDatabasePreparationScript.sql")
public abstract class CustomAcceptanceTest {
    private final ApplicationContextSetup applicationContextSetup = new ApplicationContextSetup();
    private final ApplicationContext applicationContext = applicationContextSetup.setupApplicationContext();


    protected AddItemToCartTester addItemToCart() {
        return new AddItemToCartTester(applicationContext);
    }

    protected ListShopItemsTester listShopItems() {
        return new ListShopItemsTester(applicationContext);
    }

    protected RemoveItemFromCartTester removeItemFromCart() {
        return new RemoveItemFromCartTester(applicationContext);
    }

    protected ListCartItemsTester listCartItems() {
        return new ListCartItemsTester(applicationContext);
    }

    protected BuyTester buyCart() {
        return new BuyTester(applicationContext);
    }

}
