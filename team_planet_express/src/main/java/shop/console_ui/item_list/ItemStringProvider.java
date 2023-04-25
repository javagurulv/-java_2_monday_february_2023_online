package shop.console_ui.item_list;

import org.springframework.stereotype.Component;
import shop.core.domain.item.Item;
import shop.core.domain.user.UserRole;

@Component
public class ItemStringProvider {

    public String get(Item item, UserRole userRole) {
        return (userRole == UserRole.MANAGER)
                ? getItemStringForManager(item)
                : getItemStringForShop(item);
    }

    private String getItemStringForManager(Item item) {
        return "id: " + item.getId() +
                ", " + item.getName() +
                ", price: " + item.getPrice() +
                ", available quantity: " + item.getAvailableQuantity();
    }

    private String getItemStringForShop(Item item) {
        return item.getName() +
                ", price: " + item.getPrice() +
                ", available quantity: " + item.getAvailableQuantity();
    }

}
