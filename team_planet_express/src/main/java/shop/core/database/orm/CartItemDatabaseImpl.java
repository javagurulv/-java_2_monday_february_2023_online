package shop.core.database.orm;

import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import jakarta.persistence.TypedQuery;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;
import shop.core.database.CartItemDatabase;
import shop.core.domain.cart_item.CartItem;

import java.util.List;
import java.util.Optional;

@Repository
@Transactional
public class CartItemDatabaseImpl implements CartItemDatabase {
    @PersistenceContext
    private EntityManager entityManager;

    @Override
    public CartItem save(CartItem cartItem) {
        entityManager.persist(cartItem);
        return cartItem;
    }

    @Override
    public Optional<CartItem> findByCartIdAndItemId(Long cartId, Long itemId) {
        TypedQuery<CartItem> query = entityManager
                .createQuery("SELECT ci FROM CartItem ci WHERE cart.id =:cart_id AND item.id =:item_id", CartItem.class);
        query.setParameter("cart_id", cartId);
        query.setParameter("item_id", itemId);
        return query.getResultStream().findFirst();
    }

    @Override
    public void deleteByID(Long id) {
        CartItem cartItem = entityManager.getReference(CartItem.class, id);
        entityManager.remove(cartItem);
    }

    @Override
    public void changeOrderedQuantity(Long id, Integer newOrderedQuantity) {
        TypedQuery<CartItem> query = entityManager
                .createQuery("UPDATE CartItem SET ordered_quantity =:ordered_quantity WHERE id =:id", CartItem.class);
        query.setParameter("id", id);
        query.setParameter("ordered_quantity", newOrderedQuantity);
    }

    @Override
    public List<CartItem> getAllCartItems() {
        TypedQuery<CartItem> query = entityManager
                .createQuery("SELECT ci FROM CartItem ci", CartItem.class);
        return query.getResultList();
    }

    @Override
    public List<CartItem> getAllCartItemsForCartId(Long cartId) {
        TypedQuery<CartItem> query = entityManager
                .createQuery("SELECT ci FROM CartItem ci WHERE cart.id =:cart_id", CartItem.class);
        query.setParameter("cart_id", cartId);
        return query.getResultList();
    }
}
