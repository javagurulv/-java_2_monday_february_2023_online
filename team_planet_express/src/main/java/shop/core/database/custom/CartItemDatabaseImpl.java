//package shop.core.database.custom;
//
//import jakarta.persistence.EntityManager;
//import jakarta.persistence.PersistenceContext;
//import jakarta.persistence.criteria.CriteriaBuilder;
//import jakarta.persistence.criteria.CriteriaQuery;
//import jakarta.persistence.criteria.Root;
//import org.springframework.stereotype.Repository;
//import shop.core.database.CartItemDatabase;
//import shop.core.database.CartItemRepository;
//import shop.core.domain.cart.Cart;
//import shop.core.domain.cart_item.CartItem;
//import shop.core.domain.cart_item.CartItem_;
//import shop.core.domain.item.Item;
//
//import java.util.List;
//import java.util.Optional;
//
//@Repository
//public class CartItemDatabaseImpl implements CartItemRepository {
//    @PersistenceContext
//    private EntityManager entityManager;
//
//    @Override
//    public CartItem save(CartItem cartItem) {
//        entityManager.persist(cartItem);
//        return cartItem;
//    }
//
//    @Override
//    public Optional<CartItem> findByCartIdAndItemId(Long cartId, Long itemId) {
//        CriteriaBuilder cb = entityManager.getCriteriaBuilder();
//        CriteriaQuery<CartItem> cr = cb.createQuery(CartItem.class);
//        Root<CartItem> root = cr.from(CartItem.class);
//        cr.select(root).where(
//                cb.equal(root.get(CartItem_.cart), entityManager.getReference(Cart.class, cartId)),
//                cb.equal(root.get(CartItem_.item), entityManager.getReference(Item.class, itemId))
//        );
//        return entityManager.createQuery(cr).getResultStream().findFirst();
//    }
//
//    @Override
//    public void deleteByID(Long id) {
//        CartItem cartItem = entityManager.getReference(CartItem.class, id);
//        entityManager.remove(cartItem);
//    }
//
//    @Override
//    public void changeOrderedQuantity(Long id, Integer newOrderedQuantity) {
//        CartItem cartItem = entityManager.getReference(CartItem.class, id);
//        cartItem.setOrderedQuantity(newOrderedQuantity);
//    }
//
//    @Override
//    public List<CartItem> getAllCartItems() {
//        CriteriaBuilder cb = entityManager.getCriteriaBuilder();
//        CriteriaQuery<CartItem> cr = cb.createQuery(CartItem.class);
//        Root<CartItem> root = cr.from(CartItem.class);
//        return entityManager.createQuery(cr.select(root)).getResultList();
//    }
//
//    @Override
//    public List<CartItem> getAllCartItemsForCartId(Long cartId) {
//        CriteriaBuilder cb = entityManager.getCriteriaBuilder();
//        CriteriaQuery<CartItem> cr = cb.createQuery(CartItem.class);
//        Root<CartItem> root = cr.from(CartItem.class);
//        cr.select(root).where(
//                cb.equal(root.get(CartItem_.cart), entityManager.getReference(Cart.class, cartId))
//        );
//        return entityManager.createQuery(cr).getResultList();
//    }
//}
