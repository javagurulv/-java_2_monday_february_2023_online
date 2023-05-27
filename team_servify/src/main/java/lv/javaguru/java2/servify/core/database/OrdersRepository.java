package lv.javaguru.java2.servify.core.database;

import jakarta.transaction.Transactional;
import lv.javaguru.java2.servify.core.domain.Order;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.util.List;

//@Repository
//@Transactional
public class OrdersRepository {
    @Autowired
    private SessionFactory sessionFactory;

    public List<Order> getAll() {
        return sessionFactory.getCurrentSession()
                .createQuery("SELECT o FROM Order o", Order.class)
                .getResultList();
    }
}
