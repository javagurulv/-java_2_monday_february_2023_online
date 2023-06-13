package shop.core.database.orm;

import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;
import shop.core.database.CartRepository;
import shop.core.domain.Cart;
import shop.core.enums.CartStatus;

import java.util.List;
import java.util.Optional;

@Component
@Transactional
public class OrmCartRepositoryImpl implements CartRepository {

    @PersistenceContext
    private EntityManager entityManager;

    @Override
    public Cart save(Cart cart) {
        entityManager.persist(cart);
        return cart;
    }

    @Override
    public Optional<Cart> findOpenCartForUserId(Long userId) {
        return entityManager
                .createQuery("SELECT c FROM Cart c WHERE user.id = :user_id AND status = 'OPEN'", Cart.class)
                .setParameter("user_id", userId)
                .getResultStream()
                .findFirst();
    }

    @Override
    public void changeCartStatus(Long id, CartStatus cartStatus) {
        Cart cart = entityManager.find(Cart.class, id);
        if (cart != null) {
            cart.setStatus(cartStatus);
        }
    }

    @Override
    public List<Cart> getAllCarts() {
        return entityManager
                .createQuery("SELECT c FROM Cart c", Cart.class)
                .getResultList();
    }

}
