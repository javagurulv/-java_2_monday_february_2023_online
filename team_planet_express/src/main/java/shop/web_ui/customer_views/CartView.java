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
import shop.core_api.dto.cart_item.CartItemDTO;
import shop.core_api.entry_point.customer.BuyService;
import shop.core_api.entry_point.customer.GetListCartItemsService;
import shop.core_api.entry_point.customer.RemoveItemFromCartService;
import shop.core_api.entry_point.shared.SecurityService;
import shop.core_api.requests.customer.BuyRequest;
import shop.core_api.requests.customer.GetListCartItemsRequest;
import shop.core_api.responses.CoreError;
import shop.core_api.responses.customer.BuyResponse;
import shop.core_api.responses.customer.GetListCartItemsResponse;
import shop.web_ui.components.MainLayout;
import shop.web_ui.components.item_card.ItemCardBuilder;
import shop.web_ui.components.notification.ErrorMessage;
import shop.web_ui.components.notification.SuccessMessage;

import java.util.List;

@PageTitle("Main")
@Route(value = "cart", layout = MainLayout.class)
@PermitAll
public class CartView extends Main {
    public CartView(@Autowired GetListCartItemsService getListCartItemsService, @Autowired SecurityService securityService,
                    @Autowired RemoveItemFromCartService removeItemFromCartService, @Autowired BuyService buyService) {
        GetListCartItemsRequest request = new GetListCartItemsRequest();
        GetListCartItemsResponse response = getListCartItemsService.execute(request);

        if (response.hasErrors()) {
            for (CoreError error : response.getErrors()) {
                add(new ErrorMessage(error.getMessage()));
            }
        } else {
            List<CartItemDTO> cartItems = response.getCartItemsDTO();
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

    private VerticalLayout createList(List<CartItemDTO> cartItems, ItemCardBuilder itemCardBuilder) {
        VerticalLayout itemList = new VerticalLayout();
        itemList.setAlignItems(FlexComponent.Alignment.CENTER);
        for (CartItemDTO cartItem : cartItems) {
            itemCardBuilder.setItemIfoContent(cartItem);
            itemList.add(itemCardBuilder.build());
        }
        return itemList;
    }
}
