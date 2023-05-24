package shop.core.responses.shared;

import lombok.Getter;
import shop.core.domain.item.Item;
import shop.core.domain.user.UserRole;
import shop.core.responses.CoreError;
import shop.core.responses.CoreResponse;

import java.util.List;

@Getter
public class SearchItemResponse extends CoreResponse {

    private List<Item> items;
    private boolean nextPageAvailable;
    private UserRole userRole;

    public SearchItemResponse(List<Item> items, boolean nextPageAvailable, UserRole userRole) {
        this.items = items;
        this.nextPageAvailable = nextPageAvailable;
        this.userRole = userRole;
    }

    public SearchItemResponse(List<CoreError> errors) {
        super(errors);
    }

}
