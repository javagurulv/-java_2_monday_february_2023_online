package shop.core.database.jdbc;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Component;
import shop.core.database.CartDatabase;
import shop.core.domain.cart.Cart;
import shop.core.domain.cart.CartStatus;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

@Component
public class JdbcCartDatabaseImpl implements CartDatabase {

    @Autowired
    private JdbcTemplate jdbcTemplate;

    @Override
    public Cart save(Cart cart) {
//        cart.setId(nextId);
//        nextId++;
//        carts.add(cart);
//        return cart;
        return null;
    }

    @Override
    public Optional<Cart> findOpenCartForUserId(Long userId) {
//        return carts.stream()
//                .filter(cart -> cart.getUserId().equals(userId))
//                .filter(cart -> cart.getCartStatus().equals(CartStatus.OPEN))
//                .findFirst();
        return null;
    }

    @Override
    public void changeCartStatus(Long id, CartStatus newCartStatus) {
//        carts.stream()
//                .filter(cart -> cart.getId().equals(id))
//                .findFirst()
//                .ifPresent(cart -> cart.setCartStatus(newCartStatus));
    }

    @Override
    public void changeLastActionDate(Long id, LocalDate newLastActionDate) {
//        carts.stream()
//                .filter(cart -> cart.getId().equals(id))
//                .findFirst()
//                .ifPresent(cart -> cart.setLastActionDate(LocalDate.now()));
    }

    @Override
    public List<Cart> getAllCarts() {
//        return carts;
        return null;
    }

}
