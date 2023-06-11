package shop.core_api.responses.shared;

import lombok.Getter;
import shop.core.domain.item.Item;
import shop.core.domain.item.ItemConverter;
import shop.core_api.dto.item.ItemDTO;
import shop.core_api.responses.CoreError;
import shop.core_api.responses.CoreResponse;

import java.util.List;

@Getter
public class SearchItemResponse extends CoreResponse {

    private List<ItemDTO> itemsDTO;
    private boolean nextPageAvailable;

    public SearchItemResponse(List<Item> items, boolean nextPageAvailable) {
        this.itemsDTO = items.stream().map(ItemConverter::toItemDTO).toList();
        this.nextPageAvailable = nextPageAvailable;
    }

    public SearchItemResponse(List<CoreError> errors) {
        super(errors);
    }

}
