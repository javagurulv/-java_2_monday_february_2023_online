package shop.acceptance_tests.custom.tester;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;
import shop.core.services.actions.customer.GetListShopItemsServiceImpl;
import shop.core_api.requests.customer.GetListShopItemsRequest;
import shop.core_api.responses.customer.GetListShopItemsResponse;

import static org.junit.jupiter.api.Assertions.assertTrue;

@Component
@Transactional
public class ListShopItemsTester extends Tester {

    @Autowired
    private GetListShopItemsServiceImpl listShopItemsService;

    private GetListShopItemsResponse getListShopItemsResponse;

    public ListShopItemsTester showListShopItems() {
        GetListShopItemsRequest getListShopItemsRequest = new GetListShopItemsRequest();
        getListShopItemsResponse = listShopItemsService.execute(getListShopItemsRequest);
        return this;
    }

    public ListShopItemsTester checkItemInListShopResponse(String itemName, int quantity) {
        assertTrue(getListShopItemsResponse.getShopItemsDTO().stream()
                .anyMatch(item -> item.getName().equals(itemName) && item.getAvailableQuantity() == quantity));
        return this;
    }

    @SuppressWarnings("UnusedReturnValue")
    public ListShopItemsTester checkItemInShop(String itemName, Integer quantity) {
        super.checkItemInShop(itemName, quantity);
        return this;
    }

}
