package shop.web_ui.controllers.customer;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import shop.core.requests.customer.RemoveItemFromCartRequest;
import shop.core.responses.customer.RemoveItemFromCartResponse;
import shop.core.services.actions.customer.RemoveItemFromCartService;
import shop.core.support.CurrentUserId;

@Controller
public class RemoveItemFromCartController {

    @Autowired
    private RemoveItemFromCartService removeItemFromCartService;
    @Autowired
    private CurrentUserId currentUserId;

    @GetMapping(value = "/removeItemFromCart")
    public String showRemoveItemFromCartPage(ModelMap modelMap) {
        modelMap.addAttribute("request", new RemoveItemFromCartRequest());
        return "customer/removeItemFromCart";
    }

    @PostMapping(value = "/removeItemFromCart")
    public String processRemoveItemFromCart(
            @ModelAttribute(value = "request") RemoveItemFromCartRequest request, ModelMap modelMap) {
        request.setCurrentUserId(currentUserId);
        RemoveItemFromCartResponse response = removeItemFromCartService.execute(request);
        if (response.hasErrors()) {
            modelMap.addAttribute("errors", response.getErrors());
            return "removeItemFromCart";
        } else {
            return "redirect:/listCartItems";
        }
    }

}
