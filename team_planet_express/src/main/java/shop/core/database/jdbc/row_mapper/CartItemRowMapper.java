package shop.core.database.jdbc.row_mapper;

import org.springframework.jdbc.core.RowMapper;
import shop.core.domain.cart.Cart;
import shop.core.domain.cart_item.CartItem;
import shop.core.domain.item.Item;

import java.sql.ResultSet;
import java.sql.SQLException;

public class CartItemRowMapper implements RowMapper<CartItem> {

    @Override
    public CartItem mapRow(ResultSet rs, int rowNum) throws SQLException {
        //TODO yeet this, it does not work
        CartItem cartItem = new CartItem(
                new Cart(),//rs.getLong("cart_id"),
                new Item(),//rs.getLong("item_id"),
                rs.getInt("ordered_quantity")
        );
        cartItem.setId(rs.getLong("id"));
        return cartItem;
    }

}
