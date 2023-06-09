package shop.core_api.responses.customer;

import lombok.Getter;
import shop.core.domain.item.Item;
import shop.core_api.dto.item.ItemDTO;
import shop.core_api.responses.CoreResponse;

import java.util.List;

@Getter
public class ListShopItemsResponse extends CoreResponse {

    private final List<ItemDTO> shopItemsDTO;

    public ListShopItemsResponse(List<Item> shopItems) {
        this.shopItemsDTO = shopItems.stream().map(ItemDTO::of).toList();
    }

}
