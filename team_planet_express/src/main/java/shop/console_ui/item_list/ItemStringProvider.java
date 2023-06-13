package shop.console_ui.item_list;

import org.springframework.stereotype.Component;
import shop.core.dtos.ItemDto;

@Component
public class ItemStringProvider {

    public String get(ItemDto item) {
        return "id: " + item.getId() +
                ", " + item.getName() +
                ", price: " + item.getPrice() +
                ", available quantity: " + item.getAvailableQuantity();
    }

}
