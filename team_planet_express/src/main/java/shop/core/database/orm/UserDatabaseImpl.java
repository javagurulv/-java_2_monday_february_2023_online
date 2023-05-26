package shop.core.database.orm;

import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import jakarta.persistence.TypedQuery;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;
import shop.core.database.UserDatabase;
import shop.core.domain.user.User;

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
        TypedQuery<User> query = entityManager
                .createQuery("SELECT u FROM User u WHERE login = :login", User.class);
        query.setParameter("login", login);
        return query.getResultStream().findFirst();
    }

    @Override
    public List<User> getAllUsers() {
        return entityManager
                .createQuery("SELECT u FROM User u", User.class)
                .getResultList();
    }
}
