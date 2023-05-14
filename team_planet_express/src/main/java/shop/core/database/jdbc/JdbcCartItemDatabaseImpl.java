package shop.core.database.jdbc;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Component;
import shop.core.database.CartItemDatabase;
import shop.core.domain.cart_item.CartItem;

import java.util.List;
import java.util.Optional;

@Component
public class JdbcCartItemDatabaseImpl implements CartItemDatabase {

    @Autowired
    private JdbcTemplate jdbcTemplate;

    @Override
    public CartItem save(CartItem cartItem) {
//        cartItem.setId(nextId);
//        nextId++;
//        cartItems.add(cartItem);
//        return cartItem;
        return null;
    }

    @Override
    public Optional<CartItem> findByCartIdAndItemId(Long cartId, Long itemId) {
//        return cartItems.stream()
//                .filter(cartItem -> cartItem.getCartId().equals(cartId))
//                .filter(cartItem -> cartItem.getItemId().equals(itemId))
//                .findFirst();
        return null;
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
//        cartItems.stream()
//                .filter(cartItem -> cartItem.getId().equals(id))
//                .findFirst()
//                .ifPresent(cartItem -> cartItem.setOrderedQuantity(newOrderedQuantity));
    }

    @Override
    public List<CartItem> getAllCartItems() {
//        return cartItems;
        return null;
    }

    @Override
    public List<CartItem> getAllCartItemsForCartId(Long cartId) {
//        return cartItems.stream()
//                .filter(cartItem -> cartItem.getCartId().equals(cartId))
//                .collect(Collectors.toList());
        return null;
    }
}
