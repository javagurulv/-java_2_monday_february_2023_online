package shop.web_ui.controllers.customer;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.GetMapping;
import shop.core.requests.customer.ListShopItemsRequest;
import shop.core.responses.customer.ListShopItemsResponse;
import shop.core.services.actions.customer.ListShopItemsService;
import shop.core.support.CurrentUserId;

@Controller
public class ListShopItemsController {

    @Autowired
    private ListShopItemsService listShopItemsService;
    @Autowired
    private CurrentUserId currentUserId;

    @GetMapping(value = "/listShopItems")
    public String showListShopItemsPage(ModelMap modelMap) {
        ListShopItemsRequest request = new ListShopItemsRequest(currentUserId);
        ListShopItemsResponse response = listShopItemsService.execute(request);
        modelMap.addAttribute("items", response.getShopItems());
        return "customer/listShopItems";
    }

}
