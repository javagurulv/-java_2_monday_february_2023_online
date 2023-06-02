package shop.core.requests.manager;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class AddItemToShopRequest {

    private String itemName;
    private String price;
    private String availableQuantity;

}
