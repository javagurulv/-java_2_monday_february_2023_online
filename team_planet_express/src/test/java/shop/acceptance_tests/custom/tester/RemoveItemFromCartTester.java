package shop.acceptance_tests.custom.tester;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;
import shop.core.services.actions.customer.RemoveItemFromCartServiceImpl;
import shop.core_api.dto.cart_item.CartItemDTO;
import shop.core_api.dto.item.ItemDTO;
import shop.core_api.requests.customer.RemoveItemFromCartRequest;

@Component
@Transactional
public class RemoveItemFromCartTester extends Tester {

    @Autowired
    private RemoveItemFromCartServiceImpl removeItemFromCartService;


    public RemoveItemFromCartTester remove(CartItemDTO cartItemDTO) {
        RemoveItemFromCartRequest removeItemFromCartRequest = new RemoveItemFromCartRequest(cartItemDTO);
        removeItemFromCartService.execute(removeItemFromCartRequest);
        return this;
    }

    public RemoveItemFromCartTester notItemInCart(CartItemDTO cartItemDTO) {
        super.notItemInCart(cartItemDTO);
        return this;
    }

    @SuppressWarnings("UnusedReturnValue")
    public RemoveItemFromCartTester checkItemInShop(ItemDTO itemDTO) {
        super.checkItemInShop(itemDTO);
        return this;
    }

}
