package shop.core.database;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.stereotype.Repository;
import shop.core.domain.cart_item.CartItem;

@Repository
public interface CartItemRepository extends JpaRepository<CartItem, Long>, JpaSpecificationExecutor<CartItem> {
}
