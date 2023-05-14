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

    //TODO yeet date operations to db
    @Override
    public Cart save(Cart cart) {
        String sql = "INSERT INTO cart (user_id, status, last_update) VALUES (?, ?, ?);";
        //TODO UNBORK DATE !!!
        Object[] args = new Object[]{cart.getUserId(), cart.getCartStatus().toString(), "2023-05-14 23:59:59"};
        jdbcTemplate.update(sql, args);
        //TODO get ID
        return cart;
    }

    //TODO Optional keklol?
    @Override
    public Optional<Cart> findOpenCartForUserId(Long userId) {
        String sql = "SELECT * FROM cart WHERE user_id = ? AND status = 'OPEN';";
        Object[] args = new Object[]{userId};
        return jdbcTemplate.query(sql, new CartRowMapper(), args).stream().findFirst();
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
