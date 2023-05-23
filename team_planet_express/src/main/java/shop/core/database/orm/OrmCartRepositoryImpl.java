package shop.core.database.orm;

import org.hibernate.SessionFactory;
import org.hibernate.query.Query;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;
import shop.core.database.CartRepository;
import shop.core.domain.cart.Cart;
import shop.core.domain.cart.CartStatus;
import shop.core.domain.user.User;

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
        //TODO get ID ?
        return cart;
    }

    @Override
    public Optional<Cart> findOpenCartForUserId(User user) {
        Query<Cart> query = sessionFactory.getCurrentSession()
                .createQuery("SELECT c FROM Cart c WHERE user = :user AND status = 'OPEN'", Cart.class);
        query.setParameter("user", user);
        return query.getResultList().stream().findFirst();
    }

    @Override
    public void changeCartStatus(Long id, CartStatus cartStatus) {
        //TODO deprecated ???
        Query query = sessionFactory.getCurrentSession()
                .createQuery("UPDATE Cart SET status = :status WHERE id = :id");
        query.setParameter("status", cartStatus.toString());
        query.setParameter("id", id);
    }

    @Override
    public List<Cart> getAllCarts() {
        return sessionFactory.getCurrentSession()
                .createQuery("SELECT c FROM Cart c", Cart.class)
                .getResultList();
    }

}
