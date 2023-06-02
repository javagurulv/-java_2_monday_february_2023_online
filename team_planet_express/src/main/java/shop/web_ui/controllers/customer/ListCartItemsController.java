package shop.web_ui.controllers.customer;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.GetMapping;
import shop.core.requests.customer.ListCartItemsRequest;
import shop.core.responses.customer.ListCartItemsResponse;
import shop.core.services.actions.customer.ListCartItemsService;
import shop.core.support.CurrentUserId;

@Controller
public class ListCartItemsController {

    @Autowired
    private ListCartItemsService listCartItemsService;
    @Autowired
    private CurrentUserId currentUserId;

    @GetMapping(value = "/listCartItems")
    public String showListCartItemsPage(ModelMap modelMap) {
        ListCartItemsRequest request = new ListCartItemsRequest(currentUserId);
        ListCartItemsResponse response = listCartItemsService.execute(request);
        modelMap.addAttribute("items", response.getCartItemsForList());
        modelMap.addAttribute("total", response.getCartTotal());
        return "customer/listCartItems";
    }

}
