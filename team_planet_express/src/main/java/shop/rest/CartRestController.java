package shop.rest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import shop.core.requests.customer.AddItemToCartRequest;
import shop.core.requests.customer.BuyRequest;
import shop.core.requests.customer.ListCartItemsRequest;
import shop.core.requests.customer.RemoveItemFromCartRequest;
import shop.core.responses.customer.AddItemToCartResponse;
import shop.core.responses.customer.BuyResponse;
import shop.core.responses.customer.ListCartItemsResponse;
import shop.core.responses.customer.RemoveItemFromCartResponse;
import shop.core.services.actions.customer.AddItemToCartService;
import shop.core.services.actions.customer.BuyService;
import shop.core.services.actions.customer.ListCartItemsService;
import shop.core.services.actions.customer.RemoveItemFromCartService;
import shop.core.support.CurrentUserId;

@RestController
@RequestMapping("/cart")
public class CartRestController {

    @Autowired
    private ListCartItemsService listCartItemsService;
    @Autowired
    private AddItemToCartService addItemToCartService;
    @Autowired
    private RemoveItemFromCartService removeItemFromCartService;
    @Autowired
    private BuyService buyService;
    @Autowired
    private CurrentUserId currentUserId;

    @GetMapping(path = "/item/all", produces = "application/json")
    public ListCartItemsResponse getAllCartItems() {
        ListCartItemsRequest request = new ListCartItemsRequest(currentUserId);
        return listCartItemsService.execute(request);
    }

    @PostMapping(path = "/item/", consumes = "application/json", produces = "application/json")
    public AddItemToCartResponse addItemToCart(@RequestBody AddItemToCartRequest request) {
        request.setCurrentUserId(currentUserId);
        return addItemToCartService.execute(request);
    }

    @DeleteMapping(path = "/item/remove/{itemName}", produces = "application/json")
    public RemoveItemFromCartResponse deleteItemFromCart(@PathVariable String itemName) {
        RemoveItemFromCartRequest request = new RemoveItemFromCartRequest(currentUserId, itemName);
        return removeItemFromCartService.execute(request);
    }

    @PutMapping(path = "/buy", produces = "application/json")
    public BuyResponse buyCart() {
        BuyRequest request = new BuyRequest(currentUserId);
        return buyService.execute(request);
    }

}
