package shop.core_api.responses.customer;

import lombok.Getter;
import shop.core_api.dto.item.ItemDTO;
import shop.core_api.responses.CoreError;
import shop.core_api.responses.CoreResponse;

import java.util.List;

@Getter
public class GetItemResponse extends CoreResponse {
    private ItemDTO itemDTO;

    public GetItemResponse(ItemDTO itemDTO) {
        this.itemDTO = itemDTO;
    }

    public GetItemResponse(List<CoreError> errors) {
        super(errors);
    }
}
