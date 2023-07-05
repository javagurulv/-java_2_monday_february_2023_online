package shop.core.database;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.stereotype.Repository;
import shop.core.domain.cart.Cart;

@Repository
public interface CartRepository extends JpaRepository<Cart, Long>, JpaSpecificationExecutor<Cart> {
}
