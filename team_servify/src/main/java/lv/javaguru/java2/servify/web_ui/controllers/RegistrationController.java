package lv.javaguru.java2.servify.web_ui.controllers;

import lv.javaguru.java2.servify.core.dto.RegistrationDTO;
import lv.javaguru.java2.servify.core.services.RegistrationService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.*;

@Controller
@CrossOrigin("*")
public class RegistrationController {
    @Autowired
    private RegistrationService registrationService;

    @GetMapping("/registr")
    public String registrationPage(ModelMap modelMap) {
        modelMap.addAttribute("regUser", new RegistrationDTO());
        return "registr";
    }

    @PostMapping("/registr")
    public String registerUser(@ModelAttribute("regUser") RegistrationDTO registrationDTO){
         registrationService.registerUser(registrationDTO);
        return "redirect:registration?success";
    }
}
