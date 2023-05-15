package shop.core.database.jdbc.row_mapper;

import org.springframework.jdbc.core.RowMapper;
import shop.core.domain.cart.Cart;
import shop.core.domain.cart.CartStatus;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.time.LocalDate;

public class CartRowMapper implements RowMapper<Cart> {

    @Override
    public Cart mapRow(ResultSet rs, int rowNum) throws SQLException {
        Cart cart = new Cart(rs.getLong("user_id"));
        cart.setCartStatus(convertToCartStatusEnum(rs.getString("status")));
        //TODO UNBORK DATE !!!
        cart.setLastActionDate(LocalDate.now());
        cart.setId(rs.getLong("id"));
        return cart;
    }

    //TODO TEMPTRASH
    private CartStatus convertToCartStatusEnum(String status) {
        if ("OPEN".equals(status)) {
            return CartStatus.OPEN;
        } else {
            return CartStatus.CLOSED;
        }
    }

}
