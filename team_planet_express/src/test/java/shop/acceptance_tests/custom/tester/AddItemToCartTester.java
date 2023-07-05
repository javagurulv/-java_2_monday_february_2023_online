package shop.acceptance_tests.custom.tester;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;
import shop.core_api.dto.cart_item.CartItemDTO;
import shop.core_api.dto.item.ItemDTO;
import shop.core_api.entry_point.customer.AddItemToCartService;
import shop.core_api.requests.customer.AddItemToCartRequest;

@Component
@Transactional
public class AddItemToCartTester extends Tester {

    @Autowired
    private AddItemToCartService addItemToCartService;

    public AddItemToCartTester add(CartItemDTO cartItemDTO) {
        AddItemToCartRequest addItemToCartRequest = new AddItemToCartRequest(cartItemDTO);
        addItemToCartService.execute(addItemToCartRequest);
        return this;
    }

    public AddItemToCartTester checkItemInCart(CartItemDTO cartItemDTO) {
        super.checkItemInCart(cartItemDTO);
        return this;
    }

    @SuppressWarnings("UnusedReturnValue")
    public AddItemToCartTester checkItemInShop(ItemDTO itemDTO) {
        super.checkItemInShop(itemDTO);
        return this;
    }

}
