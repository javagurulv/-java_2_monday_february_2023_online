package shop.core_api.responses.customer;

import lombok.Getter;
import shop.core.domain.item.Item;
import shop.core.domain.item.ItemConverter;
import shop.core_api.dto.item.ItemDTO;
import shop.core_api.responses.CoreResponse;

import java.util.List;

@Getter
public class GetListShopItemsResponse extends CoreResponse {

    private final List<ItemDTO> shopItemsDTO;

    public GetListShopItemsResponse(List<Item> shopItems) {
        this.shopItemsDTO = shopItems.stream().map(ItemConverter::toItemDTO).toList();
    }

}
