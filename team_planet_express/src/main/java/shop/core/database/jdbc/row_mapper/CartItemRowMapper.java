package shop.core.database.jdbc.row_mapper;

import org.springframework.jdbc.core.RowMapper;
import shop.core.domain.cart_item.CartItem;

import java.sql.ResultSet;
import java.sql.SQLException;

public class CartItemRowMapper implements RowMapper<CartItem> {

    @Override
    public CartItem mapRow(ResultSet rs, int rowNum) throws SQLException {
        CartItem cartItem = new CartItem(
                rs.getLong("cart_id"),
                rs.getLong("item_id"),
                rs.getInt("ordered_quantity")
        );
        cartItem.setId(rs.getLong("id"));
        return cartItem;
    }

}
