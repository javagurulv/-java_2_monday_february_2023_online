package shop.core.database.orm;

import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import org.hibernate.query.Query;
import org.springframework.stereotype.Repository;
import shop.core.database.CartRepository;
import shop.core.domain.cart.Cart;
import shop.core.domain.cart.CartStatus;

import java.util.List;
import java.util.Optional;

@Repository
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
        Query<Cart> query = (Query<Cart>) entityManager
                .createQuery("SELECT c FROM Cart c WHERE user.id = :user_id AND status = 'OPEN'", Cart.class);
        query.setParameter("user_id", userId);
        return query.getResultStream().findFirst();
    }

    @Override
    public void changeCartStatus(Long id, CartStatus cartStatus) {
        Cart cart = entityManager.find(Cart.class, id);
        if (cart != null) {
            cart.setStatus(cartStatus);
            entityManager.merge(cart);
        }
    }

    @Override
    public List<Cart> getAllCarts() {
        return entityManager
                .createQuery("SELECT c FROM Cart c", Cart.class)
                .getResultList();
    }

}
