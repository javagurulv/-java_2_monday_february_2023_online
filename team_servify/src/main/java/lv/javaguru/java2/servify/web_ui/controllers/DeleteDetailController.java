package lv.javaguru.java2.servify.web_ui.controllers;

import lv.javaguru.java2.servify.core.dto.requests.AddDetailRequest;
import lv.javaguru.java2.servify.core.dto.requests.GetAllDetailsRequest;
import lv.javaguru.java2.servify.core.dto.requests.RemoveDetailRequest;
import lv.javaguru.java2.servify.core.dto.responses.GetAllDetailResponse;
import lv.javaguru.java2.servify.core.dto.responses.RemoveDetailResponse;
import lv.javaguru.java2.servify.core.services.details.GetAllDetailsService;
import lv.javaguru.java2.servify.core.services.details.RemoveDetailService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;

@Controller
public class DeleteDetailController {
//    @Autowired
//    private GetAllDetailsService getAllDetailsService;
    @Autowired
    private RemoveDetailService removeDetailService;

//    @GetMapping(value = "/removeDetail")
//    public String showAllDetails(ModelMap modelMap) {
//        GetAllDetailResponse response = getAllDetailsService.getAll(
//                new GetAllDetailsRequest()
//        );
//        modelMap.addAttribute("detail", response.getDetails());
//        return "/removeDetail";
//    }

    @GetMapping(value = "/deleteDetail")
    public String deleteDetails(ModelMap modelMap) {
        modelMap.addAttribute("request", new RemoveDetailRequest());
        return "deleteDetail";
    }


    @PostMapping("/deleteDetail")
    public String processDeleteDetailRequest(@ModelAttribute(value = "request") RemoveDetailRequest request, ModelMap modelMap) {
        RemoveDetailResponse response = removeDetailService.execute(request);
        if (response.hasErrors()) {
            modelMap.addAttribute("errors", response.getErrors());
        }
        return "redirect:/showAllDetails";
    }
}
