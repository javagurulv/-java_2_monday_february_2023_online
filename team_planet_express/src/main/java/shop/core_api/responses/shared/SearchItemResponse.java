package shop.core_api.responses.shared;

import lombok.Getter;
import shop.core.domain.item.Item;
import shop.core_api.responses.CoreError;
import shop.core_api.responses.CoreResponse;

import java.util.List;

@Getter
public class SearchItemResponse extends CoreResponse {

    private List<Item> items;
    private boolean nextPageAvailable;

    public SearchItemResponse(List<Item> items, boolean nextPageAvailable) {
        this.items = items;
        this.nextPageAvailable = nextPageAvailable;
    }

    public SearchItemResponse(List<CoreError> errors) {
        super(errors);
    }

}
