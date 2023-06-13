package shop.core.database;

import shop.core.domain.Cart;
import shop.core.enums.CartStatus;

import java.util.List;
import java.util.Optional;

public interface CartRepository {

    Cart save(Cart cart);

    Optional<Cart> findOpenCartForUserId(Long userId);

    void changeCartStatus(Long id, CartStatus cartStatus);

    List<Cart> getAllCarts();

}
