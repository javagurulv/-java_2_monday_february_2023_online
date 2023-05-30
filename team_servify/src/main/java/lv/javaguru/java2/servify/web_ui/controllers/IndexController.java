package lv.javaguru.java2.servify.web_ui.controllers;

import org.springframework.web.bind.annotation.GetMapping;

public class IndexController {
    @GetMapping(value = "/")
    public String index() {
        return "index";
    }
}
