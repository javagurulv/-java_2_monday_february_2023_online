package shop.core.database.orm;

import org.hibernate.SessionFactory;
import org.hibernate.query.Query;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;
import shop.core.database.CartItemRepository;
import shop.core.domain.cart_item.CartItem;

import java.util.List;
import java.util.Optional;

@Component
@Transactional
public class OrmCartItemRepositoryImpl implements CartItemRepository {

    @Autowired
    private SessionFactory sessionFactory;

    @Override
    public CartItem save(CartItem cartItem) {
        sessionFactory.getCurrentSession().persist(cartItem);
        return cartItem;
    }

    @Override
    public Optional<CartItem> findByCartIdAndItemId(Long cartId, Long itemId) {
        Query<CartItem> query = sessionFactory.getCurrentSession()
                .createQuery("SELECT ci FROM CartItem ci WHERE cart.id = :cart_id AND item.id = :item_id", CartItem.class);
        query.setParameter("cart_id", cartId);
        query.setParameter("item_id", itemId);
        return query.getResultStream().findFirst();
    }

    @Override
    public void deleteByID(Long id) {
        CartItem cartItem = sessionFactory.getCurrentSession().get(CartItem.class, id);
        if (cartItem != null) {
            sessionFactory.getCurrentSession().remove(cartItem);
        }
    }

    @Override
    public void changeOrderedQuantity(Long id, Integer newOrderedQuantity) {
        CartItem cartItem = sessionFactory.getCurrentSession().get(CartItem.class, id);
        if (cartItem != null) {
            cartItem.setOrderedQuantity(newOrderedQuantity);
            sessionFactory.getCurrentSession().merge(cartItem);
        }
    }

    @Override
    public List<CartItem> getAllCartItems() {
        return sessionFactory.getCurrentSession()
                .createQuery("SELECT ci FROM CartItem ci", CartItem.class)
                .getResultList();
    }

    @Override
    public List<CartItem> getAllCartItemsForCartId(Long cartId) {
        Query<CartItem> query = sessionFactory.getCurrentSession()
                .createQuery("SELECT ci FROM CartItem ci WHERE cart.id = :cart_id", CartItem.class);
        query.setParameter("cart_id", cartId);
        return query.getResultList();
    }

}
