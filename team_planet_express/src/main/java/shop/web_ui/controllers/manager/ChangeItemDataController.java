package shop.web_ui.controllers.manager;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import shop.core.requests.manager.ChangeItemDataRequest;
import shop.core.responses.manager.ChangeItemDataResponse;
import shop.core.services.actions.manager.ChangeItemDataService;

@Controller
public class ChangeItemDataController {

    @Autowired
    private ChangeItemDataService changeItemDataService;

    @GetMapping(value = "/changeItemData")
    public String showChangeItemDataPage(ModelMap modelMap) {
        modelMap.addAttribute("request", new ChangeItemDataRequest());
        return "manager/changeItemData";
    }

    @PostMapping(value = "changeItemData")
    public String processChangeItemDataRequest(
            @ModelAttribute(value = "request") ChangeItemDataRequest request, ModelMap modelMap) {
        ChangeItemDataResponse response = changeItemDataService.execute(request);
        if (response.hasErrors()) {
            modelMap.addAttribute("errors", response.getErrors());
            return "manager/changeItemData";
        } else {
            return "redirect:/";
        }
    }

}
