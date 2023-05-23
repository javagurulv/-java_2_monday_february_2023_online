package shop.core.database.jdbc;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.support.GeneratedKeyHolder;
import org.springframework.jdbc.support.KeyHolder;
import org.springframework.stereotype.Component;
import shop.core.database.CartItemRepository;
import shop.core.database.jdbc.row_mapper.CartItemRowMapper;
import shop.core.domain.cart.Cart;
import shop.core.domain.cart_item.CartItem;
import shop.core.domain.item.Item;

import java.sql.PreparedStatement;
import java.sql.Statement;
import java.util.List;
import java.util.Optional;

//@Component
public class JdbcCartItemRepositoryImpl implements CartItemRepository {

    @Autowired
    private JdbcTemplate jdbcTemplate;

    @Override
    public CartItem save(CartItem cartItem) {
        String sql = "INSERT INTO cart_item (cart_id, item_id, ordered_quantity) VALUES (?, ?, ?);";
        KeyHolder keyHolder = new GeneratedKeyHolder();
        jdbcTemplate.update(connection -> {
            PreparedStatement statement = connection.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS);
            statement.setLong(1, cartItem.getCart().getId());
            statement.setLong(2, cartItem.getCart().getId());
            statement.setInt(3, cartItem.getOrderedQuantity());
            return statement;
        }, keyHolder);
        if (keyHolder.getKey() != null) {
            cartItem.setId(keyHolder.getKey().longValue());
        }
        return cartItem;
    }

    @Override
    public Optional<CartItem> findByCartIdAndItemId(Cart cart, Item item) {
        String sql = "SELECT * FROM cart_item WHERE cart_id = ? AND item_id = ?;";
        Object[] args = new Object[]{cart.getId(), item.getId()};
        return jdbcTemplate.query(sql, new CartItemRowMapper(), args).stream().findFirst();
    }

    @Override
    public void deleteByID(Long idToRemove) {
        String sql = "DELETE FROM cart_item WHERE id = ?";
        Object[] args = new Object[]{idToRemove};
        jdbcTemplate.update(sql, args);
    }

    @Override
    public void changeOrderedQuantity(Long id, Integer newOrderedQuantity) {
        String sql = "UPDATE cart_item SET ordered_quantity = ? WHERE id = ?;";
        Object[] args = new Object[]{newOrderedQuantity, id};
        jdbcTemplate.update(sql, args);
    }

    @Override
    public List<CartItem> getAllCartItems() {
        String sql = "SELECT * FROM cart_item;";
        return jdbcTemplate.query(sql, new CartItemRowMapper());
    }

    @Override
    public List<CartItem> getAllCartItemsForCartId(Cart cart) {
        String sql = "SELECT * FROM cart_item WHERE cart_id = ?;";
        Object[] args = new Object[]{cart.getId()};
        return jdbcTemplate.query(sql, new CartItemRowMapper(), args);
    }

}