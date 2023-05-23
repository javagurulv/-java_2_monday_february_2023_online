package shop.core.database.orm;

import org.hibernate.SessionFactory;
import org.hibernate.query.Query;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;
import shop.core.database.CartItemRepository;
import shop.core.domain.cart.Cart;
import shop.core.domain.cart_item.CartItem;
import shop.core.domain.item.Item;

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
    public Optional<CartItem> findByCartIdAndItemId(Cart cart, Item item) {
        Query<CartItem> query = sessionFactory.getCurrentSession()
                .createQuery("SELECT ci FROM CartItem ci WHERE cart = :cart AND item = :item", CartItem.class);
        query.setParameter("cart", cart);
        query.setParameter("item", item);
        return query.getResultList().stream().findFirst();
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
    public List<CartItem> getAllCartItemsForCartId(Cart cart) {
        Query<CartItem> query = sessionFactory.getCurrentSession()
                .createQuery("SELECT ci FROM CartItem ci WHERE cart = :cart", CartItem.class);
        query.setParameter("cart", cart);
        return query.getResultList();
    }

}
