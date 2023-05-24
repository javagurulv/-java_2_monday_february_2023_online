package shop.core.database.orm;

import org.hibernate.SessionFactory;
import org.hibernate.query.Query;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;
import shop.core.database.CartDatabase;
import shop.core.domain.cart.Cart;
import shop.core.domain.cart.CartStatus;

import java.util.List;
import java.util.Optional;

@Component
@Transactional
public class CartDatabaseImpl implements CartDatabase {
    @Autowired
    private SessionFactory sessionFactory;

    @Override
    public Cart save(Cart cart) {
        sessionFactory.getCurrentSession().persist(cart); // after calling persist(cart) it will be automatically add id field to cart
        return cart;
    }

    @Override
    public Optional<Cart> findOpenCartForUserId(Long userId) {
        Query<Cart> query = sessionFactory.getCurrentSession()
                .createQuery("SELECT cart FROM Cart cart WHERE user.id = :user_id AND status = 'OPEN'", Cart.class);
        query.setParameter("user_id", userId);
        return query.uniqueResultOptional();
    }

    @Override
    public void changeCartStatus(Long id, CartStatus cartStatus) {
        Query<Cart> query = sessionFactory.getCurrentSession()
                .createQuery("UPDATE Cart SET status = :status WHERE id = :id", Cart.class);
        query.setParameter("status", cartStatus);
        query.setParameter("id", id);
    }

    @Override
    public List<Cart> getAllCarts() {
        Query<Cart> query = sessionFactory.getCurrentSession()
                .createQuery("SELECT cart FROM Cart cart", Cart.class);
        return query.getResultList();
    }
}
