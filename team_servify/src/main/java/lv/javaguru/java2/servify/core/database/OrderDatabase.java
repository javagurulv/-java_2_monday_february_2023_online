package lv.javaguru.java2.servify.core.database;

import lv.javaguru.java2.servify.domain.Order;
import lv.javaguru.java2.servify.domain.OrderStatus;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

public interface OrderDatabase {
    void save(Order order);
    void changeOrderStatus(Long orderId, OrderStatus orderStatus);
    void changeOrderDate(Long orderId, LocalDate orderDate);
    List<Order> getAllOrders();
    Optional<Order> findOrderByUserId(Long userId);
}
