package shop.core.responses.shared;

import lombok.Getter;
import shop.core.domain.item.Item;
import shop.core.responses.CoreError;
import shop.core.responses.CoreResponse;

import java.util.List;

@Getter
public class GetItemResponse extends CoreResponse {

    private Item item;

    public GetItemResponse(Item item) {
        this.item = item;
    }

    public GetItemResponse(List<CoreError> errors) {
        super(errors);
    }

}
