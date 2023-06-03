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
import shop.core.services.actions.customer.AddItemToCartService;
import shop.core.services.actions.shared.SecurityService;
import shop.web_ui.components.ItemCard;
import shop.web_ui.components.MainLayout;

import java.util.Optional;

@PageTitle("Main")
@Route(value = "item", layout = MainLayout.class)
@AnonymousAllowed
public class ItemView extends Main implements HasUrlParameter<Long> {
    private Item item;
    @Autowired
    ItemRepository itemRepository;
    @Autowired
    SecurityService securityService;
    @Autowired
    AddItemToCartService addItemToCartService;
    private ItemCard itemCard;

    @Override
    public void setParameter(BeforeEvent event, Long id) {
        Optional<Item> byId = itemRepository.findById(id);
        item = byId.get();
        itemCard = new ItemCard(item);
        add(itemCard);
        add(createBuyDiv());
    }


    private Div createBuyDiv() {
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
                        authenticatedUserFromDB.get(),
                        item.getName(),
                        quantity.getValue().toString()
                );
                addItemToCartService.execute(addItemToCartRequest);
                Optional<Item> byId = itemRepository.findById(item.getId());
                item = byId.get();
                quantity.setMax(item.getAvailableQuantity());
                quantity.setValue(0);
                itemCard.updateQuantity(item.getAvailableQuantity().toString());
            }
        });
        div.add(quantity);
        div.add(addToCartButton);
        return div;
    }
}
