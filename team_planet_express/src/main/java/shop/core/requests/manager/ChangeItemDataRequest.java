package shop.core.requests.manager;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class ChangeItemDataRequest {

    private String itemId;
    private String newItemName;
    private String newPrice;
    private String newAvailableQuantity;

}
