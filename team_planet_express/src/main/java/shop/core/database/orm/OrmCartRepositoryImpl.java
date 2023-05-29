package shop.core.database.orm;

import org.hibernate.SessionFactory;
import org.hibernate.query.Query;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;
import shop.core.database.CartRepository;
import shop.core.domain.cart.Cart;
import shop.core.domain.cart.CartStatus;

import java.util.List;
import java.util.Optional;

@Component
@Transactional
public class OrmCartRepositoryImpl implements CartRepository {

    @Autowired
    private SessionFactory sessionFactory;

    @Override
    public Cart save(Cart cart) {
        sessionFactory.getCurrentSession().persist(cart);
        return cart;
    }

    @Override
    public Optional<Cart> findOpenCartForUserId(Long userId) {
        Query<Cart> query = sessionFactory.getCurrentSession()
                .createQuery("SELECT c FROM Cart c WHERE user.id = :user_id AND status = 'OPEN'", Cart.class);
        query.setParameter("user_id", userId);
        return query.getResultStream().findFirst();
    }

    @Override
    public void changeCartStatus(Long id, CartStatus cartStatus) {
        Cart cart = sessionFactory.getCurrentSession().get(Cart.class, id);
        if (cart != null) {
            cart.setStatus(cartStatus);
            sessionFactory.getCurrentSession().merge(cart);
        }
    }

    @Override
    public List<Cart> getAllCarts() {
        return sessionFactory.getCurrentSession()
                .createQuery("SELECT c FROM Cart c", Cart.class)
                .getResultList();
    }

}
