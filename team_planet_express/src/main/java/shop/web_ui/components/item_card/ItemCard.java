package shop.web_ui.components.item_card;

import com.vaadin.flow.component.orderedlayout.HorizontalLayout;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class ItemCard extends HorizontalLayout {
    private ItemInfoCard itemInfoCard;
    private ImageItemCard image;
}
