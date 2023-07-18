package lv.fitness_app.web_ui.controllers;

import lv.fitness_app.core.requests.LoginUserRequest;
import lv.fitness_app.core.responses.LoginUserResponse;
import lv.fitness_app.core.services.LoginUserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;

@Controller
public class LoginController {

    @Autowired
    private LoginUserService loginUserService;

    @GetMapping(value = "/login")
    public String showLoginPage(ModelMap modelMap) {
        modelMap.addAttribute("request", new LoginUserRequest());
        return "login";
    }

    @PostMapping("/login")
    public String processLoginClientRequest(@ModelAttribute(value = "request") LoginUserRequest request, ModelMap modelMap) {
        LoginUserResponse response = loginUserService.execute(request);
        if (response.hasErrors()) {
            modelMap.addAttribute("errors", response.getErrors());
            return "login";
        } else if (response.isUserLogged()){
            return "redirect:/searchExercise";
        }else {
            modelMap.addAttribute("Email or Password incorrect", response.getErrors());
            return "login";
        }
    }
}
