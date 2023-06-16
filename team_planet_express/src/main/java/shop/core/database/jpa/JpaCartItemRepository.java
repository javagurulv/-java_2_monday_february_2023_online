package shop.core.database.jpa;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import shop.core.domain.Cart;
import shop.core.domain.CartItem;

import java.util.List;

@Repository
public interface JpaCartItemRepository extends JpaRepository<CartItem, Long> {

    List<CartItem> findByCart(Cart cart);

    @Query("SELECT ci FROM CartItem ci WHERE cart.id = :cart_id AND item.id = :item_id")
    List<CartItem> findByCartIdAndItemId(@Param(value = "cart_id") Long cartId, @Param(value = "item_id") Long itemId);

    @Modifying
    @Query("UPDATE CartItem ci SET ci.orderedQuantity = :new_ordered_quantity WHERE ci.id = :id")
    void updateOrderedQuantity(@Param(value = "id") Long id, @Param(value = "new_ordered_quantity") Integer newOrderedQuantity);

}
