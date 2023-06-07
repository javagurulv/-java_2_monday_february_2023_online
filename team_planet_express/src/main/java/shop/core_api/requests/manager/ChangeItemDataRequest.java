package shop.core_api.requests.manager;

import lombok.Value;

@Value
public class ChangeItemDataRequest {

    String itemId;
    String newItemName;
    String newPrice;
    String newAvailableQuantity;

}
