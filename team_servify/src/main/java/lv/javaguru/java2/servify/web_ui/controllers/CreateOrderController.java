package lv.javaguru.java2.servify.web_ui.controllers;

import lv.javaguru.java2.servify.core.dto.requests.OrderRequest;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
//@RequestMapping("/order")
public class CreateOrderController {

    @GetMapping("/create-order")
    public String showOrderForm(Model model) {
        model.addAttribute("orderRequest", new OrderRequest());
        return "create-order";
    }

    @PostMapping("/create")
    public String createOrder(@ModelAttribute("orderRequest") OrderRequest orderRequest, BindingResult bindingResult) {
        // Perform validation if necessary

        // Call the createOrder method and pass the necessary parameters from the orderRequest object

        // Example usage:
        // Order createdOrder = createOrder(orderRequest.getPrincipal(), orderRequest.getOrderItemRequests(), orderRequest.getNotes(), orderRequest.getOrderStatus());

        // Process the created order as needed

        return "redirect:/order/success"; // Redirect to a success page
    }

    @GetMapping("/success")
    public String showOrderSuccessPage() {
        return "order-success";
    }
}

