package shop.core.domain;

import jakarta.persistence.*;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@Entity
@Table(name = "cart_item")
public class CartItem {

    @Id
    @Column(name = "id", nullable = false)
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @ManyToOne
    @JoinColumn(name = "cart_id", nullable = false)
    private Cart cart;
    @ManyToOne
    @JoinColumn(name = "item_id", nullable = false)
    private Item item;
    @Column(name = "ordered_quantity", nullable = false)
    private Integer orderedQuantity;

    public CartItem(Cart cart, Item item, Integer orderedQuantity) {
        this.cart = cart;
        this.item = item;
        this.orderedQuantity = orderedQuantity;
    }

}
