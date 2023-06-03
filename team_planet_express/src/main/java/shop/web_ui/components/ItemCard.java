package shop.web_ui.components;

import com.vaadin.flow.component.Component;
import com.vaadin.flow.component.Text;
import com.vaadin.flow.component.html.Div;
import com.vaadin.flow.component.html.Image;
import com.vaadin.flow.component.orderedlayout.HorizontalLayout;
import com.vaadin.flow.component.orderedlayout.VerticalLayout;
import com.vaadin.flow.server.StreamResource;
import shop.core.domain.item.Item;

import java.io.ByteArrayInputStream;

public class ItemCard extends HorizontalLayout {
    private final VerticalLayout itemInfo = new VerticalLayout();
    private Image image;
    private Text quantity;

    public ItemCard(Item item) {
        itemInfo.add(createTextField(item.getName()));
        itemInfo.add(createTextField(item.getPrice().toPlainString()));
        Div container = new Div();
        quantity = new Text(item.getAvailableQuantity().toString());
        container.add(quantity);
        itemInfo.add(container);
        //image.setSrc();
        add(createTextField("Here will be photo"), itemInfo);
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
