package shop.core.database.in_memory;

import lombok.Data;
import shop.core.database.CartRepository;
import shop.core.domain.cart.Cart;
import shop.core.domain.cart.CartStatus;
import shop.core.domain.user.User;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Data
public class InMemoryCartRepositoryImpl implements CartRepository {

    private Long nextId = 1L;
    private final List<Cart> carts = new ArrayList<>();

    @Override
    public Cart save(Cart cart) {
        cart.setId(nextId);
        nextId++;
        carts.add(cart);
        return cart;
    }

    @Override
    public Optional<Cart> findOpenCartForUserId(User user) {
        return carts.stream()
                .filter(cart -> cart.getUser().getId().equals(user.getId()))
                .filter(cart -> cart.getStatus().equals(CartStatus.OPEN.toString()))
                .findFirst();
    }

    @Override
    public void changeCartStatus(Long id, CartStatus newCartStatus) {
        carts.stream()
                .filter(cart -> cart.getId().equals(id))
                .findFirst()
                .ifPresent(cart -> cart.setStatus(newCartStatus.toString()));
    }

    public void changeLastActionDate(Long id, LocalDateTime newLastActionDate) {
        carts.stream()
                .filter(cart -> cart.getId().equals(id))
                .findFirst()
                .ifPresent(cart -> cart.setLastUpdate(newLastActionDate));
    }

    @Override
    public List<Cart> getAllCarts() {
        return carts;
    }

}
