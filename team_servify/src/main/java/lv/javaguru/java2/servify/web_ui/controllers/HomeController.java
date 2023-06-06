package lv.javaguru.java2.servify.web_ui.controllers;

import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

import java.security.Principal;

@Controller
public class HomeController {
    @GetMapping(value = "/")
    public String homePage(Principal principal) {
        if (principal != null) {
            System.out.println(((Authentication)principal).getAuthorities() + " " + principal.getName());
        }
        return "home";
    }
    @GetMapping(value = "/index")
    public String indexPage() {
        return "index";
    }

    @GetMapping("/admin")
    public String adminPage(Principal principal) {
        return "admin";
    }
    @GetMapping("/user")
    public String userPage() {
        return "user";
    }
}
