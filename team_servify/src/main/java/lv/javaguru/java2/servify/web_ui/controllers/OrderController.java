package lv.javaguru.java2.servify.web_ui.controllers;

import lv.javaguru.java2.servify.core.domain.Order;
import lv.javaguru.java2.servify.core.dto.requests.GetAllDetailsRequest;
import lv.javaguru.java2.servify.core.dto.requests.OrderItemRequest;
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
import java.util.ArrayList;
import java.util.List;


@Controller
public class OrderController {
    @Autowired
    private OrderService orderService;
    @Autowired
    private GetAllColorsService getAllColorsService;
    @Autowired
    private GetAllDetailsService getAllDetailsService;

    @GetMapping("/orders")
    public String listOrders(Principal principal, Model model) {
        List<Order> orders = orderService.listOrderItems(principal);
        model.addAttribute("orders", orders);
        model.addAttribute("userName", principal.getName());
        return "order-list";
    }

    @GetMapping("/orderPage")
    public String showColorPage(Model model, Principal principal) {
        GetAllColorsResponse response = getAllColorsService.getAll();
        model.addAttribute("colors", response.getColors());

        GetAllDetailResponse responseDetail = getAllDetailsService.getAll(new GetAllDetailsRequest());
        model.addAttribute("details", responseDetail.getDetails());

        List<Order> orders = orderService.listOrderItems(principal);
        model.addAttribute("orders", orders);
        model.addAttribute("order", new Order());

        return "/orderPage";
    }

    @GetMapping("/create")
    public String showCreateOrderPage(Model model) {
        model.addAttribute("orderItemRequest", new OrderItemRequest());
        model.addAttribute("details", getAllDetailsService.getAll(new GetAllDetailsRequest()));
        model.addAttribute("colors", getAllColorsService.getAll());
        return "orderPage";
    }

    @PostMapping("/create")
    public String createOrder(Principal principal,
                              @ModelAttribute("orderItemRequest") OrderItemRequest orderItemRequest,
                              @RequestParam(value = "notes", required = false) String notes,
                              @RequestParam(value = "orderStatus", required = false) String orderStatus) {
        List<OrderItemRequest> orderItemRequests = new ArrayList<>();
        orderItemRequests.add(orderItemRequest);

        Order order = orderService.createOrder(principal, orderItemRequests, notes, orderStatus);
        // Redirect to a success page or display a success message
        return "redirect:/orders/" + order.getId();
    }

}