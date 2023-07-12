package lv.javaguru.java2.servify.web_ui.controllers;

import lv.javaguru.java2.servify.core.dto.requests.UpdateDetailRequest;
import lv.javaguru.java2.servify.core.dto.responses.UpdateDetailResponse;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.security.Principal;

@Controller
public class HomeController {
    @GetMapping("/test")
    public String testPage() {
        return "/test";
    }
    @GetMapping(value = "/")
    public String rootPage(Model model, Principal principal) {
        boolean loggedIn = (principal != null);
        model.addAttribute("loggedIn", loggedIn);
        if (principal != null) {
            model.addAttribute("userName", principal.getName());
            System.out.println(((Authentication)principal).getAuthorities() + " "
                    + principal.getName() + " "
                    + ((Authentication) principal).getDetails());
        }
        return "home";
    }

    @GetMapping(value = "/main-menu")
    public String indexPage() {
        return "main-menu";
    }

    @GetMapping("/admin")
    public String adminPage(Principal principal) {
        return "admin";
    }
    @GetMapping("/user")
    public String userPage() {
        return "user";
    }
    @GetMapping("/login")
    public String loginPage() {
        return "login";
    }
    @GetMapping("/logout")
    public String logoutPage() {
        return "logout";
    }
    @GetMapping(value = "/home")
    public String homePage(Model model, Principal principal) {
        boolean loggedIn = (principal != null);
        model.addAttribute("loggedIn", loggedIn);
        if (principal != null) {
            System.out.println(((Authentication)principal).getAuthorities() + " "
                    + principal.getName() + " "
                    + ((Authentication) principal).getDetails());
        }
        return "home";
    }


}
