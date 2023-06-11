package shop.acceptance_tests.custom.tester;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;
import shop.core.domain.item.Item;
import shop.core.services.actions.customer.GetListCartItemsServiceImpl;
import shop.core_api.requests.customer.GetListCartItemsRequest;
import shop.core_api.responses.customer.GetListCartItemsResponse;

import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertTrue;

@Component
@Transactional
public class ListCartItemsTester extends Tester {

    @Autowired
    private GetListCartItemsServiceImpl listCartItemsService;

    private GetListCartItemsResponse getListCartItemsResponse;

    public ListCartItemsTester showListCartItems() {
        GetListCartItemsRequest getListCartItemsRequest = new GetListCartItemsRequest();
        getListCartItemsResponse = listCartItemsService.execute(getListCartItemsRequest);
        return this;
    }

    @SuppressWarnings("UnusedReturnValue")
    public ListCartItemsTester checkItemInCartResponse(String itemName, int quantity) {
        Optional<Item> itemOptional = itemRepository.getAllItems().stream()
                .filter(item -> item.getName().equals(itemName)).findFirst();
        assertTrue(itemOptional.isPresent());
        assertTrue(getListCartItemsResponse.getCartItemsDTO().stream()
                .anyMatch(cartItem -> cartItem.getItemDTO().getName().equals(itemName) && cartItem.getOrderedQuantity().equals(quantity)));
        return this;
    }

    @Override
    public ListCartItemsTester checkItemInCart(String itemName, Integer quantity) {
        super.checkItemInCart(itemName, quantity);
        return this;
    }

}
