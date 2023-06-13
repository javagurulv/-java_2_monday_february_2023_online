package shop.core.responses.shared;

import lombok.Getter;
import shop.core.dtos.ItemDto;
import shop.core.responses.CoreError;
import shop.core.responses.CoreResponse;

import java.util.List;

@Getter
public class GetItemResponse extends CoreResponse {

    private ItemDto item;

    public GetItemResponse(ItemDto item) {
        this.item = item;
    }

    public GetItemResponse(List<CoreError> errors) {
        super(errors);
    }

}
