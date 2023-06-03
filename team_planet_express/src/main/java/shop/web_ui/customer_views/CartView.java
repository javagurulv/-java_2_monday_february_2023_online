package shop.web_ui.customer_views;

import com.vaadin.flow.component.html.Main;
import com.vaadin.flow.router.PageTitle;
import com.vaadin.flow.router.Route;
import jakarta.annotation.security.PermitAll;
import org.springframework.beans.factory.annotation.Autowired;
import shop.core.domain.cart_item.CartItem;
import shop.core.requests.customer.ListCartItemsRequest;
import shop.core.responses.customer.ListCartItemsResponse;
import shop.core.services.actions.customer.ListCartItemsService;
import shop.core.services.actions.customer.RemoveItemFromCartService;
import shop.core.services.actions.shared.SecurityService;
import shop.web_ui.components.CartItemsListComponent;
import shop.web_ui.components.MainLayout;

import java.util.List;

@PageTitle("Main")
@Route(value = "cart", layout = MainLayout.class)
@PermitAll
public class CartView extends Main {
    public CartView(@Autowired ListCartItemsService listCartItemsService, @Autowired SecurityService securityService,
                    @Autowired RemoveItemFromCartService removeItemFromCartService) {
        ListCartItemsRequest request = new ListCartItemsRequest(securityService.getAuthenticatedUserFromDB());
        ListCartItemsResponse response = listCartItemsService.execute(request);
        List<CartItem> cartItems = response.getCartItems();
        add(new CartItemsListComponent(cartItems, removeItemFromCartService, securityService));
    }
}
