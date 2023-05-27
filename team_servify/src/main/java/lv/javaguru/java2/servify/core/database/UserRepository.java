package lv.javaguru.java2.servify.core.database;

import jakarta.transaction.Transactional;
import lv.javaguru.java2.servify.core.domain.UserEntity;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.util.List;
@Repository
@Transactional
public class UserRepository {
    @Autowired
    private SessionFactory sessionFactory;
    public List<UserEntity> getAllUsers() {
        return sessionFactory.getCurrentSession()
                .createQuery("SELECT u FROM UserEntity u", UserEntity.class)
                .getResultList();
    }

    public UserEntity findBiId(Long id) {
        return sessionFactory.getCurrentSession().get(UserEntity.class, id);
    }
    public void save(UserEntity user) {
        sessionFactory.getCurrentSession().save(user);
    }
}