package lv.javaguru.java2.servify.web_ui.controllers.rest;

import lv.javaguru.java2.servify.core.domain.Order;
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
    private OrderService orderService;

    @GetMapping("/orders")
    public String listOrders(Principal principal, Model model) {
        List<Order> orders = orderService.listOrderItems(principal);
        model.addAttribute("orders", orders);
        model.addAttribute("userName", principal.getName());
        return "order-list";
    }


    // Example of a form submission to create a new order
//    @RequestMapping(value = "/orders", method = RequestMethod.POST)
//    public String createOrder(OrderForm orderForm) {
//        // Process the orderForm and create the order
//        // orderService.createOrder(orderForm);
//
//        // Redirect to the list of orders
//        return "redirect:/orders";
//    }

}