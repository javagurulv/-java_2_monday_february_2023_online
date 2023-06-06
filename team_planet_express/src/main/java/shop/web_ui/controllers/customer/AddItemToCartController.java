package shop.web_ui.controllers.customer;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import shop.core.requests.customer.AddItemToCartRequest;
import shop.core.responses.customer.AddItemToCartResponse;
import shop.core.services.actions.customer.AddItemToCartService;
import shop.core.support.CurrentUserId;

@Controller
public class AddItemToCartController {

    @Autowired
    private AddItemToCartService addItemToCartService;
    @Autowired
    private CurrentUserId currentUserId;

    @GetMapping(value = "/addItemToCart")
    public String showAddItemToCartPage(ModelMap modelMap) {
        modelMap.addAttribute("request", new AddItemToCartRequest());
        return "customer/addItemToCart";
    }

    @PostMapping(value = "/addItemToCart")
    public String processAddItemToCartRequest(
            @ModelAttribute(value = "request") AddItemToCartRequest request, ModelMap modelMap) {
        request.setCurrentUserId(currentUserId);
        AddItemToCartResponse response = addItemToCartService.execute(request);
        if (response.hasErrors()) {
            modelMap.addAttribute("errors", response.getErrors());
            return "customer/addItemToCart";
        } else {
            return "redirect:/";
        }
    }

}
