package lv.javaguru.java2.servify.web_ui.controllers;
import lv.javaguru.java2.servify.core.dto.requests.GetAllDetailsRequest;
import lv.javaguru.java2.servify.core.dto.responses.GetAllDetailResponse;
import lv.javaguru.java2.servify.core.services.details.GetAllDetailsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class ShowAllDetailsController {
    @Autowired private GetAllDetailsService getAllDetailsService;

    @GetMapping(value = "/showAllDetails")
    public String showAllDetails(ModelMap modelMap) {
        GetAllDetailResponse response = getAllDetailsService.getAll(new GetAllDetailsRequest());
        modelMap.addAttribute("detail", response.getDetails());
        return "/showAllDetails";
    }
}
