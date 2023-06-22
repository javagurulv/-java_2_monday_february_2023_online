package shop.core.database.jpa;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import shop.core.domain.Cart;
import shop.core.domain.CartItem;
import shop.core.domain.Item;

import java.util.List;
import java.util.Optional;

@Repository
public interface JpaCartItemRepository extends JpaRepository<CartItem, Long> {

    List<CartItem> findByCart(Cart cart);

    Optional<CartItem> findFirstByCartAndItem(Cart cart, Item item);

    @Modifying
    @Query("UPDATE CartItem ci SET ci.orderedQuantity = :new_ordered_quantity WHERE ci.id = :id")
    void updateOrderedQuantity(@Param(value = "id") Long id, @Param(value = "new_ordered_quantity") Integer newOrderedQuantity);

}
