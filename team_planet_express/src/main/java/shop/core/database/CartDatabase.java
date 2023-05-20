package shop.core.database;

import shop.core.domain.cart.Cart;
import shop.core.domain.cart.CartStatus;

import java.util.List;
import java.util.Optional;

public interface CartDatabase {

    Cart save(Cart cart);

    Optional<Cart> findOpenCartForUserId(Long userId);

    void changeCartStatus(Long id, CartStatus cartStatus);

    List<Cart> getAllCarts();

}
