package shop.core.responses.shared;

import lombok.Getter;
import shop.core.domain.item.Item;
import shop.core.responses.CoreError;
import shop.core.responses.CoreResponse;

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
