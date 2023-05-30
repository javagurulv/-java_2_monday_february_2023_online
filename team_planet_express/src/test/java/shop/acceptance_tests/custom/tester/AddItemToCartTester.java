package shop.acceptance_tests.custom.tester;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import shop.core.requests.customer.AddItemToCartRequest;
import shop.core.services.actions.customer.AddItemToCartService;

@Component
public class AddItemToCartTester extends Tester {

    @Autowired
    private AddItemToCartService addItemToCartService;

    private String itemName;
    private Integer quantity;

    public AddItemToCartTester add(String itemName, Integer quantity) {
        this.itemName = itemName;
        this.quantity = quantity;
        AddItemToCartRequest addItemToCartRequest = new AddItemToCartRequest(currentUserId, itemName, quantity.toString());
        addItemToCartService.execute(addItemToCartRequest);
        return this;
    }

    public AddItemToCartTester checkItemInCart() {
        super.checkItemInCart(itemName, quantity);
        return this;
    }

    @SuppressWarnings("UnusedReturnValue")
    public AddItemToCartTester checkItemInShop(int leftInShopQuantity) {
        super.checkItemInShop(itemName, leftInShopQuantity);
        return this;
    }

}
