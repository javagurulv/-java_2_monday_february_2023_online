package lv.fitness_app.web_ui.controllers;

import lv.fitness_app.core.requests.RemoveUserRequest;
import lv.fitness_app.core.responses.RemoveUserResponse;
import lv.fitness_app.core.services.RemoveUserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;

@Controller
public class DeleteUserController {

    @Autowired
    private RemoveUserService removeUserService;

    @GetMapping(value = "/deleteUser")
    public String showDeleteUserPage(ModelMap modelMap) {
        modelMap.addAttribute("request", new RemoveUserRequest());
        return "deleteUser";
    }

    @PostMapping("/deleteUser")
    public String processRemoveClientRequest(@ModelAttribute(value = "request") RemoveUserRequest request, ModelMap modelMap) {
        RemoveUserResponse response = removeUserService.execute(request);
        if (response.hasErrors()) {
            modelMap.addAttribute("errors", response.getErrors());
            return "deleteUser";
        } else if (response.isUserRemoved()) {
            return "redirect:/";
        } else {
            modelMap.addAttribute("Email or Password is incorrect");
            return "deleteUser";
        }
    }
}
