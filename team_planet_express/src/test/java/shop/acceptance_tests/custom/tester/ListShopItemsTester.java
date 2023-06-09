package shop.acceptance_tests.custom.tester;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import shop.core.services.actions.customer.ListShopItemsServiceImpl;
import shop.core_api.requests.customer.ListShopItemsRequest;
import shop.core_api.responses.customer.ListShopItemsResponse;

import static org.junit.jupiter.api.Assertions.assertTrue;

@Component
public class ListShopItemsTester extends Tester {

    @Autowired
    private ListShopItemsServiceImpl listShopItemsService;

    private ListShopItemsResponse listShopItemsResponse;

    public ListShopItemsTester showListShopItems() {
        ListShopItemsRequest listShopItemsRequest = new ListShopItemsRequest();
        listShopItemsResponse = listShopItemsService.execute(listShopItemsRequest);
        return this;
    }

    public ListShopItemsTester checkItemInListShopResponse(String itemName, int quantity) {
        assertTrue(listShopItemsResponse.getShopItemsDTO().stream()
                .anyMatch(item -> item.getName().equals(itemName) && item.getAvailableQuantity() == quantity));
        return this;
    }

    @SuppressWarnings("UnusedReturnValue")
    public ListShopItemsTester checkItemInShop(String itemName, Integer quantity) {
        super.checkItemInShop(itemName, quantity);
        return this;
    }

}
