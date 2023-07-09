package lv.javaguru.java2.servify.web_ui.controllers;

import lv.javaguru.java2.servify.core.domain.Order;
import lv.javaguru.java2.servify.core.dto.requests.GetAllDetailsRequest;
import lv.javaguru.java2.servify.core.dto.responses.GetAllColorsResponse;
import lv.javaguru.java2.servify.core.dto.responses.GetAllDetailResponse;
import lv.javaguru.java2.servify.core.services.GetAllColorsService;
import lv.javaguru.java2.servify.core.services.details.GetAllDetailsService;
import lv.javaguru.java2.servify.core.services.orders.OrderService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import java.security.Principal;
import java.util.List;


@Controller
public class OrderController {
    @Autowired
    private GetAllColorsService getAllColorsService;
    @Autowired
    private GetAllDetailsService getAllDetailsService;

    @GetMapping("/orderPage")
    public String showColorPage(Model model) {
        GetAllColorsResponse response = getAllColorsService.getAll();
        model.addAttribute("colors", response.getColors());

        GetAllDetailResponse responseDetail = getAllDetailsService.getAll(new GetAllDetailsRequest());
        model.addAttribute("details", responseDetail.getDetails());
        return "/orderPage";
    }

}