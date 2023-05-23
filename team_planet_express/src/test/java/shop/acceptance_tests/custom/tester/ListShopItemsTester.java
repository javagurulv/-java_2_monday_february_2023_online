package shop.acceptance_tests.custom.tester;

import org.springframework.context.ApplicationContext;
import shop.core.requests.customer.ListShopItemsRequest;
import shop.core.responses.customer.ListShopItemsResponse;
import shop.core.services.actions.customer.ListShopItemsService;
import shop.core.support.CurrentUser;

import static org.junit.jupiter.api.Assertions.assertTrue;

public class ListShopItemsTester extends Tester {

    private ListShopItemsResponse listShopItemsResponse;

    public ListShopItemsTester(ApplicationContext applicationContext) {
        super(applicationContext);
    }

    public ListShopItemsTester showListShopItems() {
        ListShopItemsService listShopItemsService = applicationContext.getBean(ListShopItemsService.class);
        ListShopItemsRequest listShopItemsRequest = new ListShopItemsRequest(applicationContext.getBean(CurrentUser.class));
        listShopItemsResponse = listShopItemsService.execute(listShopItemsRequest);
        return this;
    }

    public ListShopItemsTester checkItemInListShopResponse(String itemName, int quantity) {
        assertTrue(listShopItemsResponse.getShopItems().stream()
                .anyMatch(item -> item.getName().equals(itemName) && item.getAvailableQuantity() == quantity));
        return this;
    }

    public ListShopItemsTester checkItemInShop(String itemName, Integer quantity) {
        super.checkItemInShop(itemName, quantity);
        return this;
    }

}
