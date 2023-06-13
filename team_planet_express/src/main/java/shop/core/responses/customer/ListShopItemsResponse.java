package shop.core.responses.customer;

import lombok.Getter;
import shop.core.dtos.ItemDto;
import shop.core.responses.CoreResponse;

import java.util.List;

@Getter
public class ListShopItemsResponse extends CoreResponse {

    private final List<ItemDto> shopItems;

    public ListShopItemsResponse(List<ItemDto> shopItems) {
        this.shopItems = shopItems;
    }

}
