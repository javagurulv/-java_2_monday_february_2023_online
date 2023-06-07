package shop.core_api.responses.customer;

import lombok.Getter;
import shop.core.domain.item.Item;
import shop.core_api.responses.CoreResponse;

import java.util.List;

@Getter
public class ListShopItemsResponse extends CoreResponse {

    private final List<Item> shopItems;

    public ListShopItemsResponse(List<Item> shopItems) {
        this.shopItems = shopItems;
    }

}
