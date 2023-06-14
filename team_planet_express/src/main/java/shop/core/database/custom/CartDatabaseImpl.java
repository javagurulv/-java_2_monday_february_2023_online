//package shop.core.database.custom;
//
//import jakarta.persistence.EntityManager;
//import jakarta.persistence.PersistenceContext;
//import jakarta.persistence.criteria.CriteriaBuilder;
//import jakarta.persistence.criteria.CriteriaQuery;
//import jakarta.persistence.criteria.Root;
//import org.springframework.stereotype.Repository;
//import shop.core.database.CartDatabase;
//import shop.core.database.CartRepository;
//import shop.core.domain.cart.Cart;
//import shop.core.domain.cart.CartStatus;
//import shop.core.domain.cart.Cart_;
//import shop.core.domain.user.User;
//
//import java.util.List;
//import java.util.Optional;
//
//@Repository
//public class CartDatabaseImpl implements CartRepository {
//    @PersistenceContext
//    private EntityManager entityManager;
//
//    @Override
//    public Cart save(Cart cart) {
//        entityManager.persist(cart); // after calling persist(cart) it will be automatically add id field to cart
//        return cart;
//    }
//
//    @Override
//    public Optional<Cart> findOpenCartForUserId(Long userId) {
//        CriteriaBuilder cb = entityManager.getCriteriaBuilder();
//        CriteriaQuery<Cart> cr = cb.createQuery(Cart.class);
//        Root<Cart> root = cr.from(Cart.class);
//        cr.select(root).where(
//                cb.equal(root.get(Cart_.user), entityManager.getReference(User.class, userId)),
//                cb.equal(root.get(Cart_.status), CartStatus.OPEN)
//        );
//        return entityManager.createQuery(cr).getResultStream().findFirst();
//    }
//
//    @Override
//    public void changeCartStatus(Long id, CartStatus cartStatus) {
//        Cart cart = entityManager.getReference(Cart.class, id);
//        cart.setStatus(cartStatus);
//    }
//
//    @Override
//    public List<Cart> getAllCarts() {
//        CriteriaBuilder cb = entityManager.getCriteriaBuilder();
//        CriteriaQuery<Cart> cr = cb.createQuery(Cart.class);
//        Root<Cart> root = cr.from(Cart.class);
//        return entityManager.createQuery(cr.select(root)).getResultList();
//    }
//}
