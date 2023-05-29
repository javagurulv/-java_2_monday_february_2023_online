package shop.acceptance_tests.custom;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.jdbc.Sql;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import shop.config.ShopConfiguration;

@ExtendWith(SpringExtension.class)
@ContextConfiguration(classes = {ShopConfiguration.class})
public class BuyItemAcceptanceTest extends CustomAcceptanceTest {


    @BeforeEach
    void setup() {
        setupDefaultUser();
    }

    @Sql({"/testDatabaseTableCreation.sql", "/testDatabaseDataInsertion.sql"})
    @Test
    void shouldBuyItem() {

        listShopItemsTester
                .showListShopItems()
                .checkItemInListShopResponse("Lightspeed Briefs", 3)
                .checkItemInShop("Lightspeed Briefs", 3);
        addItemToCartTester
                .add("Lightspeed Briefs", 2)
                .checkItemInCart()
                .checkItemInShop(1);
        listShopItemsTester
                .showListShopItems()
                .checkItemInListShopResponse("Lightspeed Briefs", 1);
        removeItemFromCartTester
                .remove("Lightspeed Briefs")
                .notItemInCart()
                .checkItemInShop("Lightspeed Briefs", 3);
        listShopItemsTester
                .showListShopItems()
                .checkItemInListShopResponse("Lightspeed Briefs", 3)
                .checkItemInShop("Lightspeed Briefs", 3);
        addItemToCartTester
                .add("Lightspeed Briefs", 2).checkItemInCart()
                .checkItemInShop(1);
        listCartItemsTester
                .showListCartItems()
                .checkItemInCart("Lightspeed Briefs", 2)
                .checkItemInCartResponse("Lightspeed Briefs", 2);
        buyTester
                .buy()
                .checkCartIsClosed();
        listShopItemsTester
                .showListShopItems()
                .checkItemInListShopResponse("Lightspeed Briefs", 1)
                .checkItemInShop("Lightspeed Briefs", 1);
    }
}
