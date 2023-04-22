package shop.core.responses.shared;

import shop.core.domain.item.Item;
import shop.core.domain.user.UserRole;
import shop.core.responses.CoreError;
import shop.core.responses.CoreResponse;

import java.util.List;

public class SearchItemResponse extends CoreResponse {

    private List<Item> items;
    private Integer totalFoundItemCount;
    private UserRole userRole;

    public SearchItemResponse(List<Item> items, Integer totalFoundItemCount, UserRole userRole) {
        this.items = items;
        this.totalFoundItemCount = totalFoundItemCount;
        this.userRole = userRole;
    }

    public SearchItemResponse(List<CoreError> errors) {
        super(errors);
    }

    public List<Item> getItems() {
        return items;
    }

    public Integer getTotalFoundItemCount() {
        return totalFoundItemCount;
    }

    public UserRole getUserRole() {
        return userRole;
    }

}
