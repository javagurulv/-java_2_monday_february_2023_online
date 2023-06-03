package shop.web_ui.controllers.shared;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import shop.core.requests.shared.SearchItemRequest;
import shop.core.responses.shared.SearchItemResponse;
import shop.core.services.actions.shared.SearchItemService;
import shop.core.support.CurrentUserId;

@Controller
public class SearchItemController {

    @Autowired
    private SearchItemService searchItemService;
    @Autowired
    private CurrentUserId currentUserId;

    @GetMapping(value = "/searchItem")
    public String showSearchItemPage(ModelMap modelMap) {
        modelMap.addAttribute("request", new SearchItemRequest());
        return "shared/searchItem";
    }

    @PostMapping(value = "/searchItem")
    public String processSearchItemRequest(
            @ModelAttribute(value = "request") SearchItemRequest request, ModelMap modelMap) {
        //TODO ordering/paging
        //TODO shame
        request.setCurrentUserId(currentUserId);
        SearchItemResponse response = searchItemService.execute(request);
        if (response.hasErrors()) {
            modelMap.addAttribute("errors", response.getErrors());
        } else {
            modelMap.addAttribute("items", response.getItems());
        }
        return "shared/searchItem";
    }

}
