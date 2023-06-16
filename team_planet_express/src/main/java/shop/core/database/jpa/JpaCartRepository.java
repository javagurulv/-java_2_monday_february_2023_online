package shop.core.database.jpa;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import shop.core.domain.Cart;
import shop.core.enums.CartStatus;

import java.util.Optional;

@Repository
public interface JpaCartRepository extends JpaRepository<Cart, Long> {

    @Query("SELECT c FROM Cart c WHERE user.id = :user_id AND status = 'OPEN'")
    Optional<Cart> findOpenCartByUserId(@Param(value = "user_id") Long userId);

    @Modifying
    @Query("UPDATE Cart c SET c.status = :status WHERE c.id = :id")
    void updateCartStatus(@Param(value = "id") Long id, @Param(value = "status") CartStatus cartStatus);

}
