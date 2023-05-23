package shop.core.database.orm;

import org.hibernate.SessionFactory;
import org.hibernate.query.Query;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;
import shop.core.database.UserRepository;
import shop.core.domain.user.User;

import java.util.List;
import java.util.Optional;

@Component
@Transactional
public class OrmUserRepositoryImpl implements UserRepository {

    @Autowired
    private SessionFactory sessionFactory;

    @Override
    public User save(User user) {
        sessionFactory.getCurrentSession().persist(user);
        return user;
    }

    @Override
    public Optional<User> findById(Long userId) {
        return Optional.of(sessionFactory.getCurrentSession().get(User.class, userId));
    }

    @Override
    public Optional<User> findByLoginName(String login) {
        Query<User> query = sessionFactory.getCurrentSession()
                .createQuery("SELECT u FROM User u WHERE login = :login", User.class);
        query.setParameter("login", login);
        return query.getResultList().stream().findFirst();
    }

    @Override
    public List<User> getAllUsers() {
        return sessionFactory.getCurrentSession()
                .createQuery("SELECT u FROM User u", User.class)
                .getResultList();
    }

}
