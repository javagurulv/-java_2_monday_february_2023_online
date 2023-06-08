package shop.core.domain.cart_item;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import shop.core.domain.BaseEntity;
import shop.core.domain.cart.Cart;
import shop.core.domain.item.Item;

import java.time.LocalDateTime;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "cart_item")
public class CartItem extends BaseEntity {

    @ManyToOne
    @JoinColumn(name = "cart_id", nullable = false)
    private Cart cart;
    @ManyToOne
    @JoinColumn(name = "item_id", nullable = false)
    private Item item;
    @Column(name = "ordered_quantity", nullable = false)
    private Integer orderedQuantity;

    @SuppressWarnings("unused")
    @PostPersist
    @PostUpdate
    @PostRemove
    private void onUpdateCart() {
        this.cart.setLastUpdate(LocalDateTime.now());
    }

}
