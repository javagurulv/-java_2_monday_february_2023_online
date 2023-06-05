package lv.javaguru.java2.servify.web_ui.controllers;
import lv.javaguru.java2.servify.core.dto.requests.AddDetailRequest;
import lv.javaguru.java2.servify.core.dto.responses.AddDetailResponse;
import lv.javaguru.java2.servify.core.services.details.AddDetailService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;

@Controller
public class AddDetailController {
    @Autowired private AddDetailService addDetailService;

    @GetMapping(value = "/addDetail")
    public String showAddDetailPage(ModelMap modelMap) {
        modelMap.addAttribute("request", new AddDetailRequest());
        return "addDetail";
    }

    @PostMapping("/addDetail")
    public String processAddDetailRequest(@ModelAttribute(value = "request") AddDetailRequest request, ModelMap modelMap) {
        AddDetailResponse response = addDetailService.execute(request);
        if (response.hasErrors()) {
            modelMap.addAttribute("errors", response.getErrors());
            return "addDetail";
        } else {
            return "redirect:/showAllDetails";
        }
    }
}
