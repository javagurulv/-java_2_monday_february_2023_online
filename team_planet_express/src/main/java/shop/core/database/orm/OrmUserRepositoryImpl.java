package shop.core.database.orm;

import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import jakarta.persistence.TypedQuery;
import org.springframework.stereotype.Repository;
import shop.core.database.UserRepository;
import shop.core.domain.user.User;

import java.util.List;
import java.util.Optional;

@Repository
public class OrmUserRepositoryImpl implements UserRepository {

    @PersistenceContext
    private EntityManager entityManager;

    @Override
    public User save(User user) {
        entityManager.persist(user);
        return user;
    }

    @Override
    public Optional<User> findById(Long userId) {
        return Optional.ofNullable(entityManager.find(User.class, userId));
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
