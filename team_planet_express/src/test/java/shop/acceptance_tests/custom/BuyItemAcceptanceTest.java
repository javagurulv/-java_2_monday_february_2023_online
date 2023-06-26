package shop.acceptance_tests.custom;

import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.jdbc.Sql;

@SpringBootTest
public class BuyItemAcceptanceTest extends CustomAcceptanceTest {

    @Sql({"/testDatabaseTableCreation.sql", "/testDatabaseDataInsertion.sql"})
    @Test
    void shouldBuyItem() {
        setupDefaultUser();
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
        buyCartTester
                .buy();
        listShopItemsTester
                .showListShopItems()
                .checkItemInListShopResponse("Lightspeed Briefs", 1)
                .checkItemInShop("Lightspeed Briefs", 1);
    }

}
