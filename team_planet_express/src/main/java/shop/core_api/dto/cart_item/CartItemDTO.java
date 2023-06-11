package shop.core_api.dto.cart_item;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import shop.core_api.dto.item.ItemDTO;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class CartItemDTO {

    private Long id;
    private Long cartId;
    private ItemDTO itemDTO;
    private Integer orderedQuantity;
}
