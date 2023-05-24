package shop.core.domain.cart_item;

import lombok.*;
import shop.core.domain.cart.Cart;
import shop.core.domain.item.Item;

import javax.persistence.*;

@Entity
@Table(name = "cart_item")
@Getter
@Setter
@RequiredArgsConstructor
@NoArgsConstructor
public class CartItem {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne
    @JoinColumn(name = "cart_id")
    @NonNull
    private Cart cart;

    @ManyToOne
    @JoinColumn(name = "item_id")
    @NonNull
    private Item item;

    @Column(name = "ordered_quantity")
    @NonNull
    private Integer orderedQuantity;

    public long getItemId() {
        return item.getId();
    }

    public long getCartId() {
        return cart.getId();
    }
}
