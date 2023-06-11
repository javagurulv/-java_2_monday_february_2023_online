package shop.acceptance_tests.custom.tester;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;
import shop.core.services.actions.customer.RemoveItemFromCartServiceImpl;
import shop.core_api.requests.customer.RemoveItemFromCartRequest;

@Component
@Transactional
public class RemoveItemFromCartTester extends Tester {

    @Autowired
    private RemoveItemFromCartServiceImpl removeItemFromCartService;

    private String itemName;

    public RemoveItemFromCartTester remove(String itemName) {
        this.itemName = itemName;
        RemoveItemFromCartRequest removeItemFromCartRequest = new RemoveItemFromCartRequest(itemName);
        removeItemFromCartService.execute(removeItemFromCartRequest);
        return this;
    }

    public RemoveItemFromCartTester notItemInCart() {
        super.notItemInCart(itemName);
        return this;
    }

    @SuppressWarnings("UnusedReturnValue")
    public RemoveItemFromCartTester checkItemInShop(String itemName, Integer quantity) {
        super.checkItemInShop(itemName, quantity);
        return this;
    }

}
