package shop.core.database;

import shop.core.domain.cart.Cart;
import shop.core.domain.cart_item.CartItem;
import shop.core.domain.item.Item;

import java.util.List;
import java.util.Optional;

public interface CartItemRepository {

    CartItem save(CartItem cartItem);

    Optional<CartItem> findByCartIdAndItemId(Cart cart, Item item);

    void deleteByID(Long id);

    void changeOrderedQuantity(Long id, Integer newOrderedQuantity);

    List<CartItem> getAllCartItems();

    List<CartItem> getAllCartItemsForCartId(Cart cart);

}
