package shop.web_ui.components;

import com.vaadin.flow.component.orderedlayout.VerticalLayout;
import shop.core.domain.cart_item.CartItem;
import shop.core.services.actions.customer.RemoveItemFromCartService;
import shop.core.services.actions.shared.SecurityService;

import java.util.List;

public class CartItemsListComponent extends VerticalLayout {
    public CartItemsListComponent(List<CartItem> cartItems,
                                  RemoveItemFromCartService removeItemFromCartService,
                                  SecurityService securityService) {
        for (CartItem cartItem : cartItems) {
            CartItemCard itemCard = new CartItemCard(cartItem,
                    removeItemFromCartService,
                    securityService
            );
            add(itemCard);
        }
    }
}
