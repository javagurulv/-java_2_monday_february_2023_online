package shop.core.database.orm;

import org.hibernate.SessionFactory;
import org.hibernate.query.Query;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;
import shop.core.database.CartItemDatabase;
import shop.core.domain.cart_item.CartItem;

import java.util.List;
import java.util.Optional;

@Component
@Transactional
public class CartItemDatabaseImpl implements CartItemDatabase {
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
                .createQuery("SELECT cartItem FROM CartItem cartItem WHERE cart.id =:cart_id AND item.id =:item_id", CartItem.class);
        query.setParameter("cart_id", cartId);
        query.setParameter("item_id", itemId);
        return query.uniqueResultOptional();
    }

    @Override
    public void deleteByID(Long id) {
        CartItem cartItem = new CartItem();
        cartItem.setId(id);
        sessionFactory.getCurrentSession().delete(cartItem); // need test
    }

    @Override
    public void changeOrderedQuantity(Long id, Integer newOrderedQuantity) {
        Query<CartItem> query = sessionFactory.getCurrentSession()
                .createQuery("UPDATE CartItem SET ordered_quantity =:ordered_quantity WHERE id =:id", CartItem.class);
        query.setParameter("id", id);
        query.setParameter("ordered_quantity", newOrderedQuantity);
    }

    @Override
    public List<CartItem> getAllCartItems() {
        Query<CartItem> query = sessionFactory.getCurrentSession()
                .createQuery("SELECT cartItem FROM CartItem cartItem", CartItem.class);
        return query.getResultList();
    }

    @Override
    public List<CartItem> getAllCartItemsForCartId(Long cartId) {
        Query<CartItem> query = sessionFactory.getCurrentSession()
                .createQuery("SELECT cartItem FROM CartItem cartItem WHERE cart.id =:cart_id", CartItem.class);
        query.setParameter("cart_id", cartId);
        return query.getResultList();
    }
}
