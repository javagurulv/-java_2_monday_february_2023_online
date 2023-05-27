package shop.core.database.orm;

import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import jakarta.persistence.criteria.CriteriaBuilder;
import jakarta.persistence.criteria.CriteriaQuery;
import jakarta.persistence.criteria.Root;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;
import shop.core.database.UserDatabase;
import shop.core.domain.user.User;
import shop.core.domain.user.User_;

import java.util.List;
import java.util.Optional;

@Repository
@Transactional
public class UserDatabaseImpl implements UserDatabase {
    @PersistenceContext
    private EntityManager entityManager;

    @Override
    public User save(User user) {
        entityManager.persist(user);
        return user;
    }

    @Override
    public Optional<User> findById(Long userId) {
        User user = entityManager.find(User.class, userId);
        return Optional.ofNullable(user);
    }

    @Override
    public Optional<User> findByLoginName(String login) {
        CriteriaBuilder cb = entityManager.getCriteriaBuilder();
        CriteriaQuery<User> cr = cb.createQuery(User.class);
        Root<User> root = cr.from(User.class);

        cr.select(root).where(
                cb.equal(root.get(User_.login), login)
        );
        return entityManager.createQuery(cr).getResultStream().findFirst();
    }

    @Override
    public List<User> getAllUsers() {
        CriteriaBuilder cb = entityManager.getCriteriaBuilder();
        CriteriaQuery<User> cr = cb.createQuery(User.class);
        Root<User> root = cr.from(User.class);
        return entityManager.createQuery(cr.select(root)).getResultList();
    }
}
