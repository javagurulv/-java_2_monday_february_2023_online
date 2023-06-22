package shop.web_ui.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.GetMapping;
import shop.core.database.jpa.JpaCartItemRepository;
import shop.core.database.jpa.JpaCartRepository;
import shop.core.database.jpa.JpaUserRepository;
import shop.core.domain.Cart;
import shop.core.domain.User;
import shop.core.requests.customer.AddItemToCartRequest;
import shop.core.requests.customer.ListShopItemsRequest;
import shop.core.requests.guest.SignUpRequest;
import shop.core.requests.shared.SearchItemRequest;
import shop.core.requests.shared.SignInRequest;
import shop.core.requests.shared.SignOutRequest;
import shop.core.responses.customer.ListShopItemsResponse;
import shop.core.services.actions.customer.ListShopItemsService;
import shop.core.services.validators.universal.system.RepositoryAccessValidator;
import shop.core.support.CurrentUserId;

import java.util.Optional;

@Controller
public class IndexController {

    @Autowired
    private ListShopItemsService listShopItemsService;
    @Autowired
    private CurrentUserId currentUserId;
    @Autowired
    private JpaUserRepository userRepository;
    @Autowired
    private JpaCartRepository cartRepository;
    @Autowired
    private JpaCartItemRepository cartItemRepository;
    @Autowired
    private RepositoryAccessValidator repositoryAccessValidator;

    @GetMapping(value = "/")
    public String index(ModelMap modelMap) {
        signIn(modelMap);
        signOut(modelMap);
        signUp(modelMap);
        showUserInfo(modelMap);
        searchItem(modelMap);
        listShopItems(modelMap);
        addITemToCart(modelMap);
        return "index";
    }

    private void signIn(ModelMap modelMap) {
        modelMap.addAttribute("signInRequest", new SignInRequest());
    }

    private void signOut(ModelMap modelMap) {
        modelMap.addAttribute("signOutRequest", new SignOutRequest());
    }

    private void signUp(ModelMap modelMap) {
        modelMap.addAttribute("signUpRequest", new SignUpRequest());
    }

    private void showUserInfo(ModelMap modelMap) {
        User user = repositoryAccessValidator.getUserById(currentUserId.getValue());
        Optional<Cart> cart = cartRepository.findOpenCartByUser(user);
        modelMap.addAttribute("user", user.getName());
        modelMap.addAttribute("userRole", user.getUserRole().toString());
        modelMap.addAttribute("cartStatus", cart.isPresent() ? "Open" : "Closed");
        modelMap.addAttribute("cartItemQuantity",
                cart.map(openCart -> cartItemRepository.findByCart(openCart).size())
                        .orElse(0));
    }

    private void searchItem(ModelMap modelMap) {
        modelMap.addAttribute("searchItemRequest", new SearchItemRequest());
    }

    private void listShopItems(ModelMap modelMap) {
        ListShopItemsRequest request = new ListShopItemsRequest();
        ListShopItemsResponse response = listShopItemsService.execute(request);
        modelMap.addAttribute("shopItems", response.getShopItems());
    }

    private void addITemToCart(ModelMap modelMap) {
        modelMap.addAttribute("addItemToCartRequest", new AddItemToCartRequest());
    }

}
