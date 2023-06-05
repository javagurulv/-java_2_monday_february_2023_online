package shop.web_ui.components.item_card;

import com.vaadin.flow.component.Text;
import com.vaadin.flow.component.html.Div;
import com.vaadin.flow.component.orderedlayout.VerticalLayout;
import shop.core.domain.cart_item.CartItem;
import shop.core.domain.item.Item;
import shop.web_ui.components.Money;

import java.math.BigDecimal;

public class ItemInfoCard extends VerticalLayout {
    private Div name;
    private Div price;
    private Div quantity;
    private Text quantityText;

    public ItemInfoCard(Item item) {
        add(createTextField(item.getName()));
        add(createPriceField(item.getPrice()));
        quantity = new Div();
        quantityText = new Text("Available quantity: " + item.getAvailableQuantity().toString());
        quantity.add(quantityText);
        add(quantity);
    }

    public ItemInfoCard(CartItem cartItem) {
        Item item = cartItem.getItem();
        add(createTextField(item.getName()));
        add(createPriceField(item.getPrice()));
        quantity = new Div();
        quantityText = new Text("Ordered quantity: " + cartItem.getOrderedQuantity().toString());
        quantity.add(quantityText);
        add(quantity);
    }

    private Div createTextField(String text) {
        Div container = new Div();
        Text textField = new Text(text);
        container.add(textField);
        return container;
    }

    private Div createPriceField(BigDecimal price) {
        Div container = new Div();
        Text text = new Text(Money.dollars(price).toString());
        container.add(text);
        return container;
    }

    public void updateQuantity(String quantity) {
        this.quantityText.setText("Available quantity: " + quantity);
    }
}
