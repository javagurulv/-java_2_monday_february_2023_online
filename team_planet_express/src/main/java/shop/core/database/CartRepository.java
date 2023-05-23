package shop.core.database;

import shop.core.domain.cart.Cart;
import shop.core.domain.cart.CartStatus;
import shop.core.domain.user.User;

import java.util.List;
import java.util.Optional;

public interface CartRepository {

    Cart save(Cart cart);

    Optional<Cart> findOpenCartForUserId(User user);

    void changeCartStatus(Long id, CartStatus cartStatus);

    List<Cart> getAllCarts();

}
