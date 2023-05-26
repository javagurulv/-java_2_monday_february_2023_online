package shop.core.database.orm;

import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import jakarta.persistence.TypedQuery;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;
import shop.core.database.CartDatabase;
import shop.core.database.CartRepository;
import shop.core.domain.cart.Cart;
import shop.core.domain.cart.CartStatus;
import shop.core.domain.user.User;

import java.util.List;
import java.util.Optional;

import static shop.core.database.orm.specification.CartSpecification.isCartOpen;
import static shop.core.database.orm.specification.CartSpecification.isEqualUser;

@Repository
@Transactional
public class CartDatabaseImpl implements CartDatabase {
    @PersistenceContext
    private EntityManager entityManager;
    @Autowired
    private CartRepository cartRepository;

    @Override
    public Cart save(Cart cart) {
        entityManager.persist(cart); // after calling persist(cart) it will be automatically add id field to cart
        return cart;
    }

    @Override
    public Optional<Cart> findOpenCartForUserId(Long userId) {
        User reference = entityManager.getReference(User.class, userId);
        return cartRepository.findOne(isCartOpen().and(isEqualUser(reference)));
    }

    @Override
    public void changeCartStatus(Long id, CartStatus cartStatus) {
        Cart cart = entityManager.getReference(Cart.class, id);
        cart.setStatus(cartStatus);
    }

    @Override
    public List<Cart> getAllCarts() {
        TypedQuery<Cart> query = entityManager
                .createQuery("SELECT c FROM Cart c", Cart.class);
        return query.getResultList();
    }
}
