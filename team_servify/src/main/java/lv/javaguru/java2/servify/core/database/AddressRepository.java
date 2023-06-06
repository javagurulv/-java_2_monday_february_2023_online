package lv.javaguru.java2.servify.core.database;


import lv.javaguru.java2.servify.core.domain.Address;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

//@Repository
//@Transactional
public class AddressRepository {
    @Autowired
    private SessionFactory sessionFactory;

    public List<Address> getAll() {
        return sessionFactory.getCurrentSession()
                .createQuery("SELECT ad FROM Address ad", Address.class)
                .getResultList();
    }
}
