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
import shop.core.database.ItemRepository;
import shop.core.domain.item.Item;
import shop.core.domain.user.User;
import shop.core.requests.customer.AddItemToCartRequest;
import shop.core.responses.CoreError;
import shop.core.responses.customer.AddItemToCartResponse;
import shop.core.services.actions.customer.AddItemToCartService;
import shop.core.services.actions.shared.SecurityService;
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
    ItemRepository itemRepository;
    @Autowired
    SecurityService securityService;
    @Autowired
    AddItemToCartService addItemToCartService;
    private Item item;

    @Override
    public void setParameter(BeforeEvent event, Long id) {
        Optional<Item> byId = itemRepository.findById(id);
        item = byId.get();
        ItemCard itemCard = new ItemCardBuilder().setItemIfoContent(item).setClickable(false).build();
        add(itemCard);
        add(createBuyDiv(itemCard));
    }


    private Div createBuyDiv(ItemCard itemCard) {
        Div div = new Div();
        IntegerField quantity = new IntegerField();
        quantity.setValue(0);
        quantity.setStepButtonsVisible(true);
        quantity.setMin(0);
        quantity.setMax(item.getAvailableQuantity());
        Button addToCartButton = new Button("Add to cart", event -> {
            Optional<User> authenticatedUserFromDB = securityService.getAuthenticatedUserFromDB();
            if (authenticatedUserFromDB.isPresent()) {
                AddItemToCartRequest addItemToCartRequest = new AddItemToCartRequest(
                        item.getName(),
                        quantity.getValue().toString()
                );
                AddItemToCartResponse response = addItemToCartService.execute(addItemToCartRequest);
                if (response.hasErrors()) {
                    for (CoreError error : response.getErrors()) {
                        add(new ErrorMessage(error.getMessage()));
                    }
                } else {
                    Optional<Item> byId = itemRepository.findById(item.getId());
                    item = byId.get();
                    quantity.setMax(item.getAvailableQuantity());
                    quantity.setValue(0);
                    itemCard.getItemInfoCard().updateQuantity(item.getAvailableQuantity().toString());
                }
            }
        });
        div.add(quantity);
        div.add(addToCartButton);
        return div;
    }
}
