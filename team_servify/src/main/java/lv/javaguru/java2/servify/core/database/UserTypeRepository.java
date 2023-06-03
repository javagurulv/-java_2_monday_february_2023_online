package lv.javaguru.java2.servify.core.database;

import lv.javaguru.java2.servify.core.domain.UserType;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.util.List;
@Repository
public class UserTypeRepository {
    @Autowired
    private SessionFactory sessionFactory;
    public List<UserType> getAllUsers() {
        return sessionFactory.getCurrentSession()
                .createQuery("SELECT ut FROM UserType ut", UserType.class)
                .getResultList();
    }
}
