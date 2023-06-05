package shop.web_ui.components.item_card;

import com.vaadin.flow.component.html.Div;
import com.vaadin.flow.component.html.Image;
import com.vaadin.flow.server.StreamResource;
import shop.core.domain.item.Item;

import java.io.ByteArrayInputStream;

public class ImageItemCard extends Div {
    private Image generateImage(Item item) {
        StreamResource sr = new StreamResource("item", () -> {
            return new ByteArrayInputStream(item.getItemPicture());
        });
        sr.setContentType("image/png");
        Image image = new Image(sr, "profile-picture");
        return image;
    }
}
