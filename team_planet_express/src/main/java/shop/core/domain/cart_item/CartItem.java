package shop.core.domain.cart_item;

import jakarta.persistence.*;
import lombok.Data;
import lombok.NoArgsConstructor;
import shop.core.domain.cart.Cart;
import shop.core.domain.item.Item;

@Data
@NoArgsConstructor
@Entity
@Table(name = "cart_item")
public class CartItem {

    @Id
    @Column(name = "id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @ManyToOne
    @JoinColumn(name = "cart_id", nullable = false)
    private Cart cart;
    @ManyToOne
    @JoinColumn(name = "item_id", nullable = false)
    private Item item;
    @Column(name = "ordered_quantity")
    private Integer orderedQuantity;

    public CartItem(Cart cart, Item item, Integer orderedQuantity) {
        this.cart = cart;
        this.item = item;
        this.orderedQuantity = orderedQuantity;
    }

}
