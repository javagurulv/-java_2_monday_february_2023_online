package shop.rest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import shop.core.requests.customer.RemoveItemFromCartRequest;
import shop.core.responses.customer.RemoveItemFromCartResponse;
import shop.core.services.actions.customer.RemoveItemFromCartService;
import shop.core.support.CurrentUserId;

@RestController
@RequestMapping("/cart")
public class CartRestController {

    @Autowired
    private RemoveItemFromCartService removeItemFromCartService;
    @Autowired
    private CurrentUserId currentUserId;

    @DeleteMapping(path = "/item/remove/{itemName}", produces = "application/json")
    public RemoveItemFromCartResponse deleteCartItem(@PathVariable String itemName) {
        RemoveItemFromCartRequest request = new RemoveItemFromCartRequest(currentUserId, itemName);
        return removeItemFromCartService.execute(request);
    }

}
