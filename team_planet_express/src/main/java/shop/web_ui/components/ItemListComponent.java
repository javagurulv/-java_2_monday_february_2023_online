package shop.web_ui.components;

import com.vaadin.flow.component.orderedlayout.VerticalLayout;
import shop.core.domain.item.Item;
import shop.web_ui.customer_views.ItemView;

import java.util.List;

public class ItemListComponent extends VerticalLayout {

    public ItemListComponent(List<Item> items) {
        for (Item item : items) {
            ItemCard itemCard = new ItemCard(item);
            itemCard.addClickListener(event -> this.getUI().ifPresent(ui -> ui.navigate(
                    ItemView.class, item.getId())
            ));
            add(itemCard);
        }
    }

    private void createSearchingTool() {

    }

    private void createFilterTool() {

    }
}
