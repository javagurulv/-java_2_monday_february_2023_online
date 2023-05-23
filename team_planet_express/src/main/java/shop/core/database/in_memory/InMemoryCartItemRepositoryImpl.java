package shop.core.database.in_memory;

import lombok.Data;
import shop.core.database.CartItemRepository;
import shop.core.domain.cart.Cart;
import shop.core.domain.cart_item.CartItem;
import shop.core.domain.item.Item;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Data
public class InMemoryCartItemRepositoryImpl implements CartItemRepository {

    private Long nextId = 1L;
    private final List<CartItem> cartItems = new ArrayList<>();

    @Override
    public CartItem save(CartItem cartItem) {
        cartItem.setId(nextId);
        nextId++;
        cartItems.add(cartItem);
        return cartItem;
    }

    @Override
    public Optional<CartItem> findByCartIdAndItemId(Cart cart, Item item) {
        return cartItems.stream()
                .filter(cartItem -> cartItem.getCart().getId().equals(cart.getId()))
                .filter(cartItem -> cartItem.getItem().getId().equals(item.getId()))
                .findFirst();
    }

    @Override
    public void deleteByID(Long idToRemove) {
        cartItems.stream()
                .filter(cartItem -> cartItem.getId().equals(idToRemove))
                .findFirst()
                .ifPresent(cartItems::remove);
    }

    @Override
    public void changeOrderedQuantity(Long id, Integer newOrderedQuantity) {
        cartItems.stream()
                .filter(cartItem -> cartItem.getId().equals(id))
                .findFirst()
                .ifPresent(cartItem -> cartItem.setOrderedQuantity(newOrderedQuantity));
    }

    @Override
    public List<CartItem> getAllCartItems() {
        return cartItems;
    }

    @Override
    public List<CartItem> getAllCartItemsForCartId(Cart cart) {
        return cartItems.stream()
                .filter(cartItem -> cartItem.getCart().getId().equals(cart.getId()))
                .collect(Collectors.toList());
    }
}
