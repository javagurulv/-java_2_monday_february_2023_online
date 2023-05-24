package shop.core.database.orm;

import org.hibernate.SessionFactory;
import org.hibernate.query.Query;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;
import shop.core.database.UserDatabase;
import shop.core.domain.user.User;

import java.util.List;
import java.util.Optional;

@Component
@Transactional
public class UserDatabaseImpl implements UserDatabase {
    @Autowired
    private SessionFactory sessionFactory;

    @Override
    public User save(User user) {
        sessionFactory.getCurrentSession().persist(user);
        return user;
    }

    @Override
    public Optional<User> findById(Long userId) {
        User user = sessionFactory.getCurrentSession().get(User.class, userId);
        return Optional.ofNullable(user);
    }

    @Override
    public Optional<User> findByLoginName(String login) {
        Query<User> query = sessionFactory.getCurrentSession()
                .createQuery("SELECT user FROM User user WHERE login = :login", User.class);
        query.setParameter("login", login);
        return query.uniqueResultOptional();
    }

    @Override
    public List<User> getAllUsers() {
        return sessionFactory.getCurrentSession()
                .createQuery("SELECT user FROM User user", User.class)
                .getResultList();
    }
}
