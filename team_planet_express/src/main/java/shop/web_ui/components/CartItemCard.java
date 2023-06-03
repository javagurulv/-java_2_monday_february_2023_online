package shop.web_ui.components;

import com.vaadin.flow.component.Component;
import com.vaadin.flow.component.Text;
import com.vaadin.flow.component.button.Button;
import com.vaadin.flow.component.html.Div;
import com.vaadin.flow.component.html.Image;
import com.vaadin.flow.component.icon.Icon;
import com.vaadin.flow.component.icon.VaadinIcon;
import com.vaadin.flow.component.orderedlayout.HorizontalLayout;
import com.vaadin.flow.component.orderedlayout.VerticalLayout;
import com.vaadin.flow.server.StreamResource;
import shop.core.domain.cart_item.CartItem;
import shop.core.domain.item.Item;
import shop.core.requests.customer.RemoveItemFromCartRequest;
import shop.core.services.actions.customer.RemoveItemFromCartService;
import shop.core.services.actions.shared.SecurityService;
import shop.web_ui.customer_views.ItemView;

import java.io.ByteArrayInputStream;

public class CartItemCard extends HorizontalLayout {
    private final VerticalLayout itemInfo = new VerticalLayout();
    private Image image;
    private Text quantity;

    public CartItemCard(CartItem cartItem, RemoveItemFromCartService removeItemFromCartService, SecurityService securityService) {
        Item item = cartItem.getItem();
        itemInfo.add(createTextField(item.getName()));
        itemInfo.add(createTextField(item.getPrice().toPlainString()));
        Div container = new Div();
        quantity = new Text(item.getAvailableQuantity().toString());
        container.add(quantity);
        itemInfo.add(container);
        //image.setSrc();
        Button deleteButton = new Button(new Icon(VaadinIcon.DEL), e -> {
            RemoveItemFromCartRequest request = new RemoveItemFromCartRequest(
                    securityService.getAuthenticatedUserFromDB().get(),
                    item.getName()
            );
            removeItemFromCartService.execute(request);
            removeFromParent();
        });

        Div wrapper = new Div();
        wrapper.add(
                createTextField("Here will be photo"),
                itemInfo,
                createTextField(cartItem.getOrderedQuantity().toString())
        );
        wrapper.addClickListener(event -> this.getUI().ifPresent(ui -> ui.navigate(
                ItemView.class, cartItem.getItem().getId())
        ));
        add(
                wrapper,
                deleteButton
        );
    }

    private Div createTextField(String text) {
        Div container = new Div();
        Text textField = new Text(text);
        container.add(textField);
        return container;
    }

    private Image generateImage(Item item) {
        StreamResource sr = new StreamResource("user", () -> {
            return new ByteArrayInputStream(item.getItemPicture());
        });
        sr.setContentType("image/png");
        Image image = new Image(sr, "profile-picture");
        return image;
    }

    private Component createButtons() {
        HorizontalLayout buttons = new HorizontalLayout();
        return buttons;
    }

    public void updateQuantity(String quantity) {
        this.quantity.setText(quantity);
    }
}
