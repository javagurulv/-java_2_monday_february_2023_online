package shop.acceptance_tests.custom.tester;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import shop.core.domain.Item;
import shop.core.requests.customer.ListCartItemsRequest;
import shop.core.responses.customer.ListCartItemsResponse;
import shop.core.services.actions.customer.ListCartItemsService;

import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertTrue;

@Component
public class ListCartItemsTester extends Tester {

    @Autowired
    private ListCartItemsService listCartItemsService;

    private ListCartItemsResponse listCartItemsResponse;

    public ListCartItemsTester showListCartItems() {
        ListCartItemsRequest listCartItemsRequest = new ListCartItemsRequest(currentUserId);
        listCartItemsResponse = listCartItemsService.execute(listCartItemsRequest);
        return this;
    }

    @SuppressWarnings("UnusedReturnValue")
    public ListCartItemsTester checkItemInCartResponse(String itemName, int quantity) {
        Optional<Item> itemOptional = itemRepository.getAllItems().stream()
                .filter(item -> item.getName().equals(itemName)).findFirst();
        assertTrue(itemOptional.isPresent());
        assertTrue(listCartItemsResponse.getCartItems().stream()
                .anyMatch(item -> item.getItemName().equals(itemName) && item.getOrderedQuantity().equals(quantity)));
        return this;
    }

    @Override
    public ListCartItemsTester checkItemInCart(String itemName, Integer quantity) {
        super.checkItemInCart(itemName, quantity);
        return this;
    }

}
