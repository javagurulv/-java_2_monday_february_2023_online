package lv.javaguru.java2.servify.core.database;

import jakarta.transaction.Transactional;
import lv.javaguru.java2.servify.core.domain.OrderItems;
import org.hibernate.SessionFactory;
import org.hibernate.query.Query;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
@Transactional
public class OrdersItemsRepository {
    @Autowired
    private SessionFactory sessionFactory;

    public List<OrderItems> getAll() {
        return sessionFactory.getCurrentSession()
                .createQuery("SELECT oi FROM OrderItems oi", OrderItems.class)
                .getResultList();
    }

    public List<OrderItems> getOrdersItemsByUserId(Long userId) {
        String hql = "SELECT oi FROM OrderItems oi " +
                     "LEFT JOIN FETCH oi.order o " +
                     "LEFT JOIN FETCH o.user u " +
                     "WHERE u.id = :userId";
        Query<OrderItems> query = sessionFactory.getCurrentSession()
                .createQuery(hql, OrderItems.class);
        query.setParameter("userId", userId);
        return query.getResultList();
    }
}
