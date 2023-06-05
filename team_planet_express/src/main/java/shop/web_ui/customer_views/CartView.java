package shop.web_ui.customer_views;

import com.vaadin.flow.component.button.Button;
import com.vaadin.flow.component.html.Main;
import com.vaadin.flow.component.orderedlayout.FlexComponent;
import com.vaadin.flow.component.orderedlayout.HorizontalLayout;
import com.vaadin.flow.component.orderedlayout.VerticalLayout;
import com.vaadin.flow.router.PageTitle;
import com.vaadin.flow.router.Route;
import jakarta.annotation.security.PermitAll;
import org.springframework.beans.factory.annotation.Autowired;
import shop.core.domain.cart_item.CartItem;
import shop.core.requests.customer.BuyRequest;
import shop.core.requests.customer.ListCartItemsRequest;
import shop.core.responses.CoreError;
import shop.core.responses.customer.BuyResponse;
import shop.core.responses.customer.ListCartItemsResponse;
import shop.core.services.actions.customer.BuyService;
import shop.core.services.actions.customer.ListCartItemsService;
import shop.core.services.actions.customer.RemoveItemFromCartService;
import shop.core.services.actions.shared.SecurityService;
import shop.web_ui.components.MainLayout;
import shop.web_ui.components.item_card.ItemCardBuilder;
import shop.web_ui.components.notification.ErrorMessage;
import shop.web_ui.components.notification.SuccessMessage;

import java.util.List;

@PageTitle("Main")
@Route(value = "cart", layout = MainLayout.class)
@PermitAll
public class CartView extends Main {
    public CartView(@Autowired ListCartItemsService listCartItemsService, @Autowired SecurityService securityService,
                    @Autowired RemoveItemFromCartService removeItemFromCartService, @Autowired BuyService buyService) {
        ListCartItemsRequest request = new ListCartItemsRequest(securityService.getAuthenticatedUserFromDB());
        ListCartItemsResponse response = listCartItemsService.execute(request);

        if (response.hasErrors()) {
            for (CoreError error : response.getErrors()) {
                add(new ErrorMessage(error.getMessage()));
            }
        } else {
            List<CartItem> cartItems = response.getCartItems();
            VerticalLayout list;
            if (cartItems != null) {
                ItemCardBuilder itemCardBuilder = new ItemCardBuilder();
                itemCardBuilder.setDelButton(securityService, removeItemFromCartService);
                list = createList(cartItems, itemCardBuilder);
                HorizontalLayout horizontalLayout = new HorizontalLayout();
                Button button = new Button("Buy", event -> {
                    BuyRequest buyRequest = new BuyRequest();
                    BuyResponse buyResponse = buyService.execute(buyRequest);
                    if (buyResponse.hasErrors()) {
                        for (CoreError error : buyResponse.getErrors()) {
                            add(new ErrorMessage(error.getMessage()));
                        }
                    } else {
                        add(new SuccessMessage("Thank you for buying"));
                    }
                    horizontalLayout.remove(list);
                });
                horizontalLayout.add(list, button);
                add(horizontalLayout);
            }
        }
    }

    private VerticalLayout createList(List<CartItem> cartItems, ItemCardBuilder itemCardBuilder) {
        VerticalLayout itemList = new VerticalLayout();
        itemList.setAlignItems(FlexComponent.Alignment.CENTER);
        for (CartItem cartItem : cartItems) {
            itemCardBuilder.setItemIfoContent(cartItem);
            itemList.add(itemCardBuilder.build());
        }
        return itemList;
    }
}
