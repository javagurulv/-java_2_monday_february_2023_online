package shop.rest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import shop.core.requests.customer.ListShopItemsRequest;
import shop.core.requests.manager.AddItemToShopRequest;
import shop.core.requests.manager.ChangeItemDataRequest;
import shop.core.requests.shared.GetItemRequest;
import shop.core.requests.shared.SearchItemRequest;
import shop.core.responses.customer.ListShopItemsResponse;
import shop.core.responses.manager.AddItemToShopResponse;
import shop.core.responses.manager.ChangeItemDataResponse;
import shop.core.responses.shared.GetItemResponse;
import shop.core.responses.shared.SearchItemResponse;
import shop.core.services.actions.customer.ListShopItemsService;
import shop.core.services.actions.manager.AddItemToShopService;
import shop.core.services.actions.manager.ChangeItemDataService;
import shop.core.services.actions.shared.GetItemService;
import shop.core.services.actions.shared.SearchItemService;
import shop.core.support.CurrentUserId;

@RestController
@RequestMapping("/item")
public class ItemRestController {

    @Autowired
    private GetItemService getItemService;
    @Autowired
    private ListShopItemsService listShopItemsService;
    @Autowired
    private SearchItemService searchItemService;
    @Autowired
    private AddItemToShopService addItemToShopService;
    @Autowired
    private ChangeItemDataService changeItemDataService;
    @Autowired
    private CurrentUserId currentUserId;

    @GetMapping(path = "/{id}", produces = "application/json")
    public GetItemResponse getItem(@PathVariable String id) {
        GetItemRequest request = new GetItemRequest(id);
        return getItemService.execute(request);
    }

    @GetMapping(path = "/all", produces = "application/json")
    public ListShopItemsResponse getAllItems() {
        ListShopItemsRequest request = new ListShopItemsRequest(currentUserId);
        return listShopItemsService.execute(request);
    }

    @GetMapping(path = "/search", produces = "application/json")
    public SearchItemResponse searchItems(@RequestParam(required = false) String itemName,
                                          @RequestParam(required = false) String price) {
        SearchItemRequest request = new SearchItemRequest(currentUserId, itemName, price, null, null);
        return searchItemService.execute(request);
    }

    @PostMapping(path = "/", consumes = "application/json", produces = "application/json")
    public AddItemToShopResponse addItem(@RequestBody AddItemToShopRequest request) {
        return addItemToShopService.execute(request);
    }

    @PutMapping(path = "/{itemId}", consumes = "application/json", produces = "application/json")
    public ChangeItemDataResponse updateItem(
            @PathVariable String itemId,
            @RequestBody ChangeItemDataRequest request) {
        request.setItemId(itemId);
        return changeItemDataService.execute(request);
    }

}
