package lv.fitness_app.core.database;

import lv.fitness_app.core.domain.User;
import org.hibernate.SessionFactory;
import org.hibernate.query.Query;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

@Component
@Transactional
public class OrmUserRepositoryImpl implements UserRepository {

    @Autowired
    private SessionFactory sessionFactory;

    @Override
    public void add(User user) {
        sessionFactory.getCurrentSession().persist(user);
    }

    @Override
    public void deleteUser(User user) {
        sessionFactory.getCurrentSession().remove(user);
    }

    @Override
    public List<User> getAllUsers() {
        return sessionFactory.getCurrentSession()
                .createQuery("SELECT u from User u", User.class)
                .getResultList();
    }

    @Override
    public User findUserByEmail(String email) {
        return sessionFactory.getCurrentSession().get(User.class, email);
    }

    @Override
    public Optional<User> getByEmail(String email) {

        User user = sessionFactory.getCurrentSession().get(User.class, email);
        if (user == null) {
            return Optional.empty();
        } else {
            return Optional.of(user);
        }
    }

    @Override
    public boolean deleteByEmail(String email) {
        Query query = sessionFactory.getCurrentSession().createQuery(
                "delete User where email = :email");
        query.setParameter("email", email);
        int result = query.executeUpdate();
        return result == 1;
    }
}
