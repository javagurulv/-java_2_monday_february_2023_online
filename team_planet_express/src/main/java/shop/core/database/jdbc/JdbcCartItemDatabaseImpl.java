package shop.core.database.jdbc;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Component;
import shop.core.database.CartItemDatabase;
import shop.core.database.jdbc.row_mapper.CartItemRowMapper;
import shop.core.domain.cart_item.CartItem;

import java.util.List;
import java.util.Optional;

@Component
public class JdbcCartItemDatabaseImpl implements CartItemDatabase {

    @Autowired
    private JdbcTemplate jdbcTemplate;

    @Override
    public CartItem save(CartItem cartItem) {
        String sql = "INSERT INTO cart_item (cart_id, item_id, ordered_quantity) VALUES (?, ?, ?);";
        Object[] args = new Object[]{cartItem.getCartId(), cartItem.getItemId(), cartItem.getOrderedQuantity()};
        jdbcTemplate.update(sql, args);
        //TODO get ID
        return cartItem;
    }

    @Override
    public Optional<CartItem> findByCartIdAndItemId(Long cartId, Long itemId) {
        String sql = "SELECT * FROM item WHERE cart_id = ? AND item_id = ?;";
        Object[] args = new Object[]{cartId, itemId};
        return jdbcTemplate.query(sql, new CartItemRowMapper(), args).stream().findFirst();
    }

    @Override
    public void deleteByID(Long idToRemove) {
        //TODO what's with nonexistent id?
        String sql = "DELETE FROM books WHERE id = ?";
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
    public List<CartItem> getAllCartItemsForCartId(Long cartId) {
        String sql = "SELECT * FROM cart_item WHERE id = ?;";
        Object[] args = new Object[]{cartId};
        return jdbcTemplate.query(sql, new CartItemRowMapper(), args);
    }

}
