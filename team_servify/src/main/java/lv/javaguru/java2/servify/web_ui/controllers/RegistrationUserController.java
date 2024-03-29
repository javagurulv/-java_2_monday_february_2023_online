package lv.javaguru.java2.servify.web_ui.controllers;

import lv.javaguru.java2.servify.core.dto.RegistrationDTO;
import lv.javaguru.java2.servify.core.services.users.RegistrationUserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.*;

@Controller
@CrossOrigin("*")
public class RegistrationUserController {
    @Autowired
    private RegistrationUserService registrationService;

    @Autowired
    private PasswordEncoder encoder;


    @GetMapping("/register")
    public String registrationPage(ModelMap modelMap) {
        modelMap.addAttribute("regUser", new RegistrationDTO());
        return "register";
    }

    @PostMapping("/register")
    public String registerUser(@ModelAttribute("regUser") RegistrationDTO registrationDTO){
        if (registrationDTO.getPassword() != null) {
            String encodedPassword = encoder.encode(registrationDTO.getPassword());
            registrationDTO.setPassword(encodedPassword);
        }
        registrationService.registerUser(registrationDTO);
        return "redirect:registration?success";
    }
}
