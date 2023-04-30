package shop.core.domain.cart_item;

import lombok.Data;

@Data
public class CartItem {

    private final Long cartId;
    private final Long itemId;
    private Long id;
    private Integer orderedQuantity;

    public CartItem(Long cartId, Long itemId, Integer orderedQuantity) {
        this.cartId = cartId;
        this.itemId = itemId;
        this.orderedQuantity = orderedQuantity;
    }

}
