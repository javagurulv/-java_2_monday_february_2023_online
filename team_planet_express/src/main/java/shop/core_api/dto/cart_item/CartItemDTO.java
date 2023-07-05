package shop.core_api.dto.cart_item;

import lombok.AllArgsConstructor;
import lombok.Data;
import shop.core_api.dto.item.ItemDTO;

@Data
@AllArgsConstructor
public class CartItemDTO {

    private Long id;
    private Long cartId;
    private final ItemDTO itemDTO;
    private final Integer orderedQuantity;
}
