package shop.web_ui.components.item_card;

import com.vaadin.flow.component.Text;
import com.vaadin.flow.component.html.Div;
import com.vaadin.flow.component.orderedlayout.VerticalLayout;
import shop.core_api.dto.cart_item.CartItemDTO;
import shop.core_api.dto.item.ItemDTO;
import shop.core_api.dto.item.Money;

public class ItemInfoCard extends VerticalLayout {
    private Div name;
    private Div price;
    private Div quantity;
    private Text quantityText;

    public ItemInfoCard(ItemDTO item) {
        add(createTextField(item.getName()));
        add(createPriceField(item.getPrice()));
        quantity = new Div();
        quantityText = new Text("Available quantity: " + item.getAvailableQuantity().toString());
        quantity.add(quantityText);
        add(quantity);
    }

    public ItemInfoCard(CartItemDTO cartItem) {
        ItemDTO item = cartItem.getItemDTO();
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

    private Div createPriceField(Money price) {
        Div container = new Div();
        Text text = new Text(price.toString());
        container.add(text);
        return container;
    }

    public void updateQuantity(String quantity) {
        this.quantityText.setText("Available quantity: " + quantity);
    }
}
