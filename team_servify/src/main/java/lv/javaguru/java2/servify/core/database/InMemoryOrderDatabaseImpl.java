package lv.javaguru.java2.servify.core.database;

import lv.javaguru.java2.servify.domain.Order;
import lv.javaguru.java2.servify.domain.OrderStatus;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

public class InMemoryOrderDatabaseImpl implements OrderDatabase{
    private Long orderId = 1L;
    private List<Order> ordersList;



    @Override
    public void save(Order order) {
        order.setOrderId(orderId);
        orderId++;
        ordersList.add(order);
    }

    @Override
    public void changeOrderStatus(Long orderId, OrderStatus newOrderStatus) {
        ordersList.stream()
                .filter(order -> order.getOrderId().equals(orderId))
                .findFirst()
                .ifPresent(order -> order.setOrderStatus(newOrderStatus));
    }

    @Override
    public void changeOrderDate(Long orderId, LocalDate orderDate) {
        ordersList.stream()
                .filter(order -> order.getOrderId().equals(orderId))
                .findFirst()
                .ifPresent(order -> order.setOrderDate(LocalDate.now()));
    }

    @Override
    public List<Order> getAllOrders() {
        return ordersList;
    }

    @Override
    public Optional<Order> findOrderByUserId(Long userId) {
        return ordersList.stream()
                .filter(order -> order.getUserId().equals(userId))
                .findFirst();
    }
}
