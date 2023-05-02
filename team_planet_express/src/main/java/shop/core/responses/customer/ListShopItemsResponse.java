package shop.core.responses.customer;

import lombok.Getter;
import shop.core.domain.item.Item;
import shop.core.domain.user.UserRole;
import shop.core.responses.CoreResponse;

import java.util.List;

@Getter
public class ListShopItemsResponse extends CoreResponse {

    private final List<Item> shopItems;
    private final UserRole userRole;

    public ListShopItemsResponse(List<Item> shopItems, UserRole userRole) {
        this.shopItems = shopItems;
        this.userRole = userRole;
    }

}
