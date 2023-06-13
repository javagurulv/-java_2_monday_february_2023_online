package lv.fitness_app.web_ui.controllers;

import lv.fitness_app.core.requests.AddUserRequest;
import lv.fitness_app.core.responses.AddUserResponse;
import lv.fitness_app.core.services.AddUserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;

@Controller
public class RegisterNewUserController {

    @Autowired
    private AddUserService addUserService;

    @GetMapping(value = "/registerNewUser")
    public String showRegisterNewUserPage(ModelMap modelMap) {
        modelMap.addAttribute("request", new AddUserRequest());
        return "registerNewUser";
    }

    @PostMapping("/registerNewUser")
    public String processAddNewClientRequest(@ModelAttribute(value = "request") AddUserRequest request, ModelMap modelMap) {
        AddUserResponse response = addUserService.execute(request);
        if (response.hasErrors()) {
            modelMap.addAttribute("errors", response.getErrors());
            return "registerNewUser";
        } else {
            return "redirect:/";
        }
    }
}
