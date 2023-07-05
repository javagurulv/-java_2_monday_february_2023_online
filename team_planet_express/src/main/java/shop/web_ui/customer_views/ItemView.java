package shop.web_ui.customer_views;

import com.vaadin.flow.component.button.Button;
import com.vaadin.flow.component.html.Div;
import com.vaadin.flow.component.html.Main;
import com.vaadin.flow.component.textfield.IntegerField;
import com.vaadin.flow.router.BeforeEvent;
import com.vaadin.flow.router.HasUrlParameter;
import com.vaadin.flow.router.PageTitle;
import com.vaadin.flow.router.Route;
import com.vaadin.flow.server.auth.AnonymousAllowed;
import org.springframework.beans.factory.annotation.Autowired;
import shop.core.domain.user.User;
import shop.core_api.dto.cart_item.CartItemDTO;
import shop.core_api.dto.item.ItemDTO;
import shop.core_api.entry_point.customer.AddItemToCartService;
import shop.core_api.entry_point.customer.GetItemService;
import shop.core_api.entry_point.shared.SecurityService;
import shop.core_api.requests.customer.AddItemToCartRequest;
import shop.core_api.requests.customer.GetItemRequest;
import shop.core_api.responses.CoreError;
import shop.core_api.responses.customer.AddItemToCartResponse;
import shop.web_ui.components.MainLayout;
import shop.web_ui.components.item_card.ItemCard;
import shop.web_ui.components.item_card.ItemCardBuilder;
import shop.web_ui.components.notification.ErrorMessage;

import java.util.Optional;

@PageTitle("Main")
@Route(value = "item", layout = MainLayout.class)
@AnonymousAllowed
public class ItemView extends Main implements HasUrlParameter<Long> {
    @Autowired
    GetItemService getItemService;
    @Autowired
    SecurityService securityService;
    @Autowired
    AddItemToCartService addItemToCartService;

    @Override
    public void setParameter(BeforeEvent event, Long id) {
        ItemDTO itemDTO = new ItemDTO();
        itemDTO.setId(id);
        ItemDTO item = getItemService.execute(new GetItemRequest(itemDTO)).getItemDTO();
        ItemCard itemCard = new ItemCardBuilder().setItemIfoContent(item).setClickable(false).build();
        add(itemCard);
        Optional<User> authenticatedUserFromDB = securityService.getAuthenticatedUserFromDB();
        if (authenticatedUserFromDB.isPresent()) {
            add(createBuyDiv(itemCard, item));
        } else {
            //LocalStorage
        }
    }


    private Div createBuyDiv(ItemCard itemCard, ItemDTO item) {
        Div div = new Div();
        IntegerField quantity = new IntegerField();
        quantity.setValue(0);
        quantity.setStepButtonsVisible(true);
        quantity.setMin(0);
        quantity.setMax(item.getAvailableQuantity());
        Button addToCartButton = new Button("Add to cart", event -> {
            ItemDTO itemDTO1 = new ItemDTO();
            itemDTO1.setId(item.getId());
            CartItemDTO cartItemDTO = new CartItemDTO(null, null, itemDTO1, quantity.getValue());
            AddItemToCartRequest addItemToCartRequest = new AddItemToCartRequest(
                    cartItemDTO
            );
            AddItemToCartResponse response = addItemToCartService.execute(addItemToCartRequest);
            if (response.hasErrors()) {
                for (CoreError error : response.getErrors()) {
                    add(new ErrorMessage(error.getMessage()));
                }
            } else {
                ItemDTO itemDTO = new ItemDTO();
                itemDTO.setId(item.getId());
                ItemDTO itemUpdate = getItemService.execute(new GetItemRequest(itemDTO)).getItemDTO();
                quantity.setMax(itemUpdate.getAvailableQuantity());
                quantity.setValue(0);
                itemCard.getItemInfoCard().updateQuantity(itemUpdate.getAvailableQuantity().toString());
            }
        }
        );
        div.add(quantity);
        div.add(addToCartButton);
        return div;
    }

}
