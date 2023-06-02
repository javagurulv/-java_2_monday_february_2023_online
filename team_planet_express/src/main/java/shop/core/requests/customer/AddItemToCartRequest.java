package shop.core.requests.customer;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import shop.core.support.CurrentUserId;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class AddItemToCartRequest {

    private CurrentUserId currentUserId;
    private String itemName;
    private String orderedQuantity;

}
