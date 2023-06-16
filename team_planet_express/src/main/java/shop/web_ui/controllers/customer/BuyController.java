package shop.web_ui.controllers.customer;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import shop.core.requests.customer.BuyRequest;
import shop.core.responses.customer.BuyResponse;
import shop.core.services.actions.customer.BuyService;
import shop.core.support.CurrentUserId;

@Controller
public class BuyController {

    @Autowired
    private BuyService buyService;
    @Autowired
    private CurrentUserId currentUserId;

    @GetMapping(value = "/buy")
    public String showBuyPage(ModelMap modelMap) {
        modelMap.addAttribute("request", new BuyRequest());
        return "customer/buy";
    }

    @PostMapping(value = "/buy")
    public String processBuyRequest(
            @ModelAttribute(value = "request") BuyRequest request, ModelMap modelMap) {
        request.setCurrentUserId(currentUserId);
        BuyResponse response = buyService.execute(request);
        if (response.hasErrors()) {
            modelMap.addAttribute("errors", response.getErrors());
            return "customer/buy";
        } else {
            return "redirect:/";
        }
    }

}
