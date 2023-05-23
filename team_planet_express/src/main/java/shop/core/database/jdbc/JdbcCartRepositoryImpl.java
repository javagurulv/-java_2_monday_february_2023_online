package shop.core.database.jdbc;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.support.GeneratedKeyHolder;
import org.springframework.jdbc.support.KeyHolder;
import shop.core.database.CartRepository;
import shop.core.database.jdbc.row_mapper.CartRowMapper;
import shop.core.domain.cart.Cart;
import shop.core.domain.cart.CartStatus;
import shop.core.domain.user.User;

import java.sql.PreparedStatement;
import java.sql.Statement;
import java.util.List;
import java.util.Optional;

//@Component
public class JdbcCartRepositoryImpl implements CartRepository {

    @Autowired
    private JdbcTemplate jdbcTemplate;

    @Override
    public Cart save(Cart cart) {
        String sql = "INSERT INTO cart (user_id, status) VALUES (?, ?);";
        KeyHolder keyHolder = new GeneratedKeyHolder();
        jdbcTemplate.update(connection -> {
            PreparedStatement statement = connection.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS);
            statement.setLong(1, cart.getUser().getId());
            statement.setString(2, cart.getStatus().toString());
            return statement;
        }, keyHolder);
        if (keyHolder.getKey() != null) {
            cart.setId(keyHolder.getKey().longValue());
        }
        return cart;
    }

    @Override
    public Optional<Cart> findOpenCartForUserId(User user) {
        String sql = "SELECT * FROM cart WHERE user_id = ? AND status = 'OPEN';";
        Object[] args = new Object[]{user.getId()};
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
