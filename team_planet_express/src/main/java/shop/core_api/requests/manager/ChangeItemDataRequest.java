package shop.core_api.requests.manager;

import lombok.Value;
import shop.core_api.dto.item.ItemDTO;

@Value
public class ChangeItemDataRequest {

    ItemDTO itemDTO;

}
