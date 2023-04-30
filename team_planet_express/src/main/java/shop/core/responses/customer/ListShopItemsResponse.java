package shop.core.responses.customer;

import shop.core.domain.item.Item;
import shop.core.domain.user.UserRole;
import shop.core.responses.CoreResponse;

import java.util.List;

public class ListShopItemsResponse extends CoreResponse {

    private final List<Item> shopItems;
    private final UserRole userRole;

    public ListShopItemsResponse(List<Item> shopItems, UserRole userRole) {
        this.shopItems = shopItems;
        this.userRole = userRole;
    }

    public List<Item> getShopItems() {
        return shopItems;
    }

    public UserRole getUserRole() {
        return userRole;
    }

}
