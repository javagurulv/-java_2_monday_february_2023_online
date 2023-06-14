package shop.web_ui.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.GetMapping;
import shop.core.database.jpa.JpaCartItemRepository;
import shop.core.database.jpa.JpaCartRepository;
import shop.core.domain.Cart;
import shop.core.requests.customer.AddItemToCartRequest;
import shop.core.requests.customer.ListShopItemsRequest;
import shop.core.requests.shared.SignInRequest;
import shop.core.responses.customer.ListShopItemsResponse;
import shop.core.services.actions.customer.ListShopItemsService;
import shop.core.support.CurrentUserId;

import java.util.Optional;

@Controller
public class IndexController {

    @Autowired
    private ListShopItemsService listShopItemsService;
    @Autowired
    private CurrentUserId currentUserId;
    @Autowired
    private JpaCartRepository cartRepository;
    @Autowired
    private JpaCartItemRepository cartItemRepository;

    @GetMapping(value = "/")
    public String index(ModelMap modelMap) {
        ListShopItemsRequest request = new ListShopItemsRequest();
        ListShopItemsResponse response = listShopItemsService.execute(request);
        modelMap.addAttribute("items", response.getShopItems());
        Optional<Cart> cart = cartRepository.findOpenCartByUserId(currentUserId.getValue()).stream().findFirst();
        modelMap.addAttribute("signInRequest", new SignInRequest());
        modelMap.addAttribute("addItemToCartRequest", new AddItemToCartRequest());
        modelMap.addAttribute("user", currentUserId);
        modelMap.addAttribute("cartStatus", cart.isPresent() ? "Open" : "Closed");
        modelMap.addAttribute("cartItemQuantity",
                cart.map(openCart -> cartItemRepository.findByCartId(openCart.getId()).size())
                        .orElse(0));
        return "index";
    }

}
