package lv.fitness_app.database;

import lv.fitness_app.core.domain.User;
import org.hibernate.SessionFactory;
import org.hibernate.query.Query;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Component
@Transactional
public class OrmUserRepositoryImpl implements UserRepository {

    @Autowired
    private SessionFactory sessionFactory;

    @Override
    public void add(User user) {sessionFactory.getCurrentSession().save(user);

    }

    @Override
    public void deleteUser(String email) {
        Query query = sessionFactory.getCurrentSession().createQuery(
                "delete User where email = :email");
        query.setParameter("email", email).executeUpdate();

    }

    @Override
    public List<User> getAllUsers() {
        return sessionFactory.getCurrentSession()
                .createQuery("SELECT b FROM User b", User.class)
                .getResultList();
    }

    @Override
    public User findUserByEmail(String email) {
        Query query = sessionFactory.getCurrentSession().createQuery(
                "select b FROM User b where email = :email");
        query.setParameter("email", email);
        return(User) query.getResultList();
    }
}
