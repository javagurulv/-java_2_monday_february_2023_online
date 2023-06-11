package shop.acceptance_tests.custom.tester;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;
import shop.core_api.entry_point.customer.AddItemToCartService;
import shop.core_api.requests.customer.AddItemToCartRequest;

@Component
@Transactional
public class AddItemToCartTester extends Tester {

    @Autowired
    private AddItemToCartService addItemToCartService;

    private String itemName;
    private Integer quantity;

    public AddItemToCartTester add(String itemName, Integer quantity) {
        this.itemName = itemName;
        this.quantity = quantity;
        AddItemToCartRequest addItemToCartRequest = new AddItemToCartRequest(itemName, quantity.toString());
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
