package shop.core.database.jdbc;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.support.GeneratedKeyHolder;
import org.springframework.jdbc.support.KeyHolder;
import org.springframework.stereotype.Component;
import shop.core.database.CartDatabase;
import shop.core.database.jdbc.row_mapper.CartRowMapper;
import shop.core.domain.cart.Cart;
import shop.core.domain.cart.CartStatus;

import java.sql.PreparedStatement;
import java.sql.Statement;
import java.util.List;
import java.util.Optional;

@Component
public class JdbcCartDatabaseImpl implements CartDatabase {

    @Autowired
    private JdbcTemplate jdbcTemplate;

    @Override
    public Cart save(Cart cart) {
        String sql = "INSERT INTO cart (user_id, status) VALUES (?, ?);";
        KeyHolder keyHolder = new GeneratedKeyHolder();
        jdbcTemplate.update(connection -> {
            PreparedStatement statement = connection.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS);
            statement.setLong(1, cart.getUserId());
            statement.setString(2, cart.getCartStatus().toString());
            return statement;
        }, keyHolder);
        if (keyHolder.getKey() != null) {
            cart.setId(keyHolder.getKey().longValue());
        }
        return cart;
    }

    @Override
    public Optional<Cart> findOpenCartForUserId(Long userId) {
        String sql = "SELECT * FROM cart WHERE user_id = ? AND status = 'OPEN';";
        Object[] args = new Object[]{userId};
        return jdbcTemplate.query(sql, new CartRowMapper(), args).stream().findFirst();
    }

    @Override
    public void changeCartStatus(Long id, CartStatus newCartStatus) {
        String sql = "UPDATE cart SET status = ? WHERE id = ?;";
        Object[] args = new Object[]{newCartStatus.toString(), id};
        jdbcTemplate.update(sql, args);
    }

    @Override
    public List<Cart> getAllCarts() {
        String sql = "SELECT * FROM cart;";
        return jdbcTemplate.query(sql, new CartRowMapper());
    }

}
