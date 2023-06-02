package shop.web_ui.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import shop.core.requests.manager.AddItemToShopRequest;
import shop.core.responses.manager.AddItemToShopResponse;
import shop.core.services.actions.manager.AddItemToShopService;

@Controller
public class AddItemToShopController {

    @Autowired
    private AddItemToShopService addItemToShopService;

    @GetMapping(value = "/addItemToShop")
    public String showAddItemToShopPage(ModelMap modelMap) {
        modelMap.addAttribute("request", new AddItemToShopRequest());
        return "addItemToShop";
    }

    @PostMapping(value = "/addItemToShop")
    public String processAddItemToShopRequest(
            @ModelAttribute(value = "request") AddItemToShopRequest request, ModelMap modelMap) {
        AddItemToShopResponse response = addItemToShopService.execute(request);
        if (response.hasErrors()) {
            modelMap.addAttribute("errors", response.getErrors());
            return "addItemToShop";
        } else {
            return "redirect:/";
        }
    }

}
