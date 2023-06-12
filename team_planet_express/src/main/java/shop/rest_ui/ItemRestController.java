package shop.rest_ui;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import shop.core_api.dto.item.ItemDTO;
import shop.core_api.entry_point.customer.GetItemService;
import shop.core_api.entry_point.customer.GetListShopItemsService;
import shop.core_api.requests.customer.GetItemRequest;
import shop.core_api.requests.customer.GetListShopItemsRequest;
import shop.core_api.responses.customer.GetItemResponse;
import shop.core_api.responses.customer.GetListShopItemsResponse;

@RestController
@RequestMapping("/rest_api/item")
public class ItemRestController {

    @Autowired
    private GetItemService getItemService;
    @Autowired
    private GetListShopItemsService getListShopItemsService;

    @GetMapping(path = "get_item/{itemName}", produces = "application/json")
    public GetItemResponse getItem(@PathVariable String itemName) {
        ItemDTO itemDTO = new ItemDTO();
        itemDTO.setName(itemName);
        GetItemRequest request = new GetItemRequest(itemDTO);
        return getItemService.execute(request);
    }

    @GetMapping(path = "get_all_items", produces = "application/json")
    public GetListShopItemsResponse getAllItems() {
        return getListShopItemsService.execute(new GetListShopItemsRequest());
    }
}
