package lv.javaguru.java2.servify.web_ui.controllers;

import lv.javaguru.java2.servify.core.dto.requests.GetAllUsersRequest;
import lv.javaguru.java2.servify.core.dto.responses.GetAllUsersResponse;
import lv.javaguru.java2.servify.core.services.users.GetAllUsersService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class ShowAllUsersController {
    @Autowired
    private GetAllUsersService getAllUsersService;

    @GetMapping(value = "/showAllUsers")
    public String showAllUsers(ModelMap modelMap) {
        GetAllUsersResponse response = getAllUsersService.getAll(
                new GetAllUsersRequest()
        );
        modelMap.addAttribute("users", response.getUsers());
        return "/showAllUsers";
    }
 }
