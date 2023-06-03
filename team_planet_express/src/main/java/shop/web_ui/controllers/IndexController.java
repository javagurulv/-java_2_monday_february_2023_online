package shop.web_ui.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.GetMapping;
import shop.core.database.CartItemRepository;
import shop.core.database.CartRepository;
import shop.core.domain.cart.Cart;
import shop.core.support.CurrentUserId;

import java.util.Optional;

@Controller
public class IndexController {

    @Autowired
    private CurrentUserId currentUserId;
    @Autowired
    private CartRepository cartRepository;
    @Autowired
    CartItemRepository cartItemRepository;

    @GetMapping(value = "/")
    public String index(ModelMap modelMap) {
        Optional<Cart> cart = cartRepository.findOpenCartForUserId(currentUserId.getValue());
        modelMap.addAttribute("user", currentUserId);
        modelMap.addAttribute("cartStatus", cart.isPresent() ? "Open" : "Closed");
        modelMap.addAttribute("cartItemQuantity",
                cart.map(openCart -> cartItemRepository.getAllCartItemsForCartId(openCart.getId()).size())
                        .orElse(0));
        return "index";
    }

}
