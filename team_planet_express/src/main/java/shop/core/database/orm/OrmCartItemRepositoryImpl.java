package shop.core.database.orm;

import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import jakarta.persistence.TypedQuery;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;
import shop.core.database.CartItemRepository;
import shop.core.domain.cart_item.CartItem;

import java.util.List;
import java.util.Optional;

@Component
@Transactional
public class OrmCartItemRepositoryImpl implements CartItemRepository {

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
                .createQuery("SELECT ci FROM CartItem ci WHERE cart.id = :cart_id AND item.id = :item_id", CartItem.class);
        query.setParameter("cart_id", cartId);
        query.setParameter("item_id", itemId);
        return query.getResultStream().findFirst();
    }

    @Override
    public void deleteByID(Long id) {
        CartItem cartItem = entityManager.find(CartItem.class, id);
        if (cartItem != null) {
            entityManager.remove(cartItem);
        }
    }

    @Override
    public void changeOrderedQuantity(Long id, Integer newOrderedQuantity) {
        CartItem cartItem = entityManager.find(CartItem.class, id);
        if (cartItem != null) {
            cartItem.setOrderedQuantity(newOrderedQuantity);
            entityManager.merge(cartItem);
        }
    }

    @Override
    public List<CartItem> getAllCartItems() {
        return entityManager
                .createQuery("SELECT ci FROM CartItem ci", CartItem.class)
                .getResultList();
    }

    @Override
    public List<CartItem> getAllCartItemsForCartId(Long cartId) {
        TypedQuery<CartItem> query = entityManager
                .createQuery("SELECT ci FROM CartItem ci WHERE cart.id = :cart_id", CartItem.class);
        query.setParameter("cart_id", cartId);
        return query.getResultList();
    }

}
