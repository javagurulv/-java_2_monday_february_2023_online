package lv.fitness_app.web_ui.controllers;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class MainPageController {

    @GetMapping(value = "/mainPage")
    public String home() { return "mainPage"; }
}
