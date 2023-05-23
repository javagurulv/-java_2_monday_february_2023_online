package shop.core.database.jdbc.row_mapper;

import org.springframework.jdbc.core.RowMapper;
import shop.core.domain.cart.Cart;
import shop.core.domain.user.User;

import java.sql.ResultSet;
import java.sql.SQLException;

public class CartRowMapper implements RowMapper<Cart> {

    @Override
    public Cart mapRow(ResultSet rs, int rowNum) throws SQLException {
        //TODO yeet this, it does not work
        Cart cart = new Cart(
                new User()//rs.getLong("user_id")
        );
        cart.setStatus(rs.getString("status"));
        cart.setLastUpdate(rs.getTimestamp("last_update").toLocalDateTime());
        cart.setId(rs.getLong("id"));
        return cart;
    }

}
