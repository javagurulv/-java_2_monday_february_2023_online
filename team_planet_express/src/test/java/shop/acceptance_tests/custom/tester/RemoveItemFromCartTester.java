package shop.acceptance_tests.custom.tester;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import shop.core.requests.customer.RemoveItemFromCartRequest;
import shop.core.services.actions.customer.RemoveItemFromCartService;

@Component
public class RemoveItemFromCartTester extends Tester {
    @Autowired
    private RemoveItemFromCartService removeItemFromCartService;
    private String itemName;


    public RemoveItemFromCartTester remove(String itemName) {
        this.itemName = itemName;
        RemoveItemFromCartRequest removeItemFromCartRequest = new RemoveItemFromCartRequest(currentUserId, itemName);
        removeItemFromCartService.execute(removeItemFromCartRequest);
        return this;
    }

    public RemoveItemFromCartTester notItemInCart() {
        super.notItemInCart(itemName);
        return this;
    }

    public RemoveItemFromCartTester checkItemInShop(String itemName, Integer quantity) {
        super.checkItemInShop(itemName, quantity);
        return this;
    }

}
