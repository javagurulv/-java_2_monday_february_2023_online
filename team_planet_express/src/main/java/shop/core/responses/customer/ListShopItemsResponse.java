package shop.core.responses.customer;

import lombok.Getter;
import shop.core.domain.item.Item;
import shop.core.responses.CoreResponse;

import java.util.List;

@Getter
public class ListShopItemsResponse extends CoreResponse {

    private final List<Item> shopItems;

    public ListShopItemsResponse(List<Item> shopItems) {
        this.shopItems = shopItems;
    }

}
