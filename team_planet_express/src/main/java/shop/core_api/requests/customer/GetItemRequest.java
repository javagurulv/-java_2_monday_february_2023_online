package shop.core_api.requests.customer;

import lombok.Value;
import shop.core_api.dto.item.ItemDTO;

@Value
public class GetItemRequest {
    ItemDTO itemDTO;
}
