package lv.javaguru.java2.servify.core.database;

import jakarta.transaction.Transactional;
import lv.javaguru.java2.servify.core.domain.Order;
import lv.javaguru.java2.servify.core.domain.UserEntity;
import org.hibernate.SessionFactory;
import org.hibernate.query.Query;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
@Transactional
public class OrdersRepository {
    @Autowired
    private SessionFactory sessionFactory;

    public List<Order> getAll() {
        return sessionFactory.getCurrentSession()
                .createQuery("SELECT o FROM Order o", Order.class)
                .getResultList();
    }

    public Order getBiId(Long id) {
        return sessionFactory.getCurrentSession().get(Order.class, id);
    }

    public List<Order> getOrders(UserEntity user) {
        Query<Order> query = sessionFactory.getCurrentSession()
                .createQuery("SELECT o FROM Order o WHERE o.user = :user", Order.class);
                query.setParameter("user", user);
                return query.getResultList();
    }

    public List<Order> getOrdersByUserId(Long id) {
        Query<Order> query = sessionFactory.getCurrentSession()
                .createQuery("SELECT o FROM Order o WHERE o.user_id = :userId", Order.class);
        query.setParameter("userId", id);
        return query.getResultList();
    }
}
