package shop.core_api.dto.cart_item;

import lombok.Data;
import lombok.NoArgsConstructor;
import shop.core_api.dto.cart.CartDTO;
import shop.core_api.dto.item.ItemDTO;

@Data
@NoArgsConstructor
public class CartItemDTO {

    private Long id;
    private CartDTO cart;
    private ItemDTO item;
    private Integer orderedQuantity;

    public CartItemDTO(CartDTO cart, ItemDTO item, Integer orderedQuantity) {
        this.cart = cart;
        this.item = item;
        this.orderedQuantity = orderedQuantity;
    }

}
