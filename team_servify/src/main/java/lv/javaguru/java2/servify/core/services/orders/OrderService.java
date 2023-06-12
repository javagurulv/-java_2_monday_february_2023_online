package lv.javaguru.java2.servify.core.services.orders;

import lv.javaguru.java2.servify.core.database.jpa.*;
import lv.javaguru.java2.servify.core.domain.*;
import lv.javaguru.java2.servify.core.dto.requests.OrderItemRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.math.BigDecimal;
import java.security.Principal;
import java.time.ZoneId;
import java.util.Date;
import java.util.List;
import java.util.stream.Collectors;

@Service
@Transactional
public class OrderService {
    @Autowired
    private JpaOrderRepository orderRepository;
    @Autowired
    private JpaOrderItemsRepository orderItemsRepository;
    @Autowired
    private JpaDetailRepository detailRepository;
    @Autowired
    private JpaColorRepository colorRepository;
    @Autowired
    private JpaUserRepository userRepository;

    public List<Order> listOrderItems(Principal principal) {
        UserEntity user = getUserByPrinciple(principal);
        return orderRepository.findByUser(user);
    }

    public Order createOrder(Principal principal, List<OrderItemRequest> orderItemRequests, String notes, String orderStatus) {
        Order order = new Order();
        order.setUser(getUserByPrinciple(principal));
        order.setOrderDate(new Date().toInstant().atZone(ZoneId.systemDefault()).toLocalDateTime());//TODO check correct format for Date
        order.setNotes(notes);
        order.setOrderStatus(orderStatus);

        List<OrderItems> orderItemsList = orderItemRequests.stream()
                .map(orderItemRequest -> {
                    OrderItems orderItems = new OrderItems();
                    orderItems.setOrder(order);

                    Detail detail = detailRepository.findById(orderItemRequest.getDetailId())
                            .orElseThrow(() -> new IllegalArgumentException("Detail not found"));

                    Color color = colorRepository.findById(orderItemRequest.getColorId())
                            .orElseThrow(() -> new IllegalArgumentException("Color not found"));

                    orderItems.setDetail(detail);
                    orderItems.setColor(color);
                    orderItems.setCountDetail(orderItemRequest.getCount());

                    return orderItems;
                })
                .collect(Collectors.toList());

        order.setOrderItemsList(orderItemsList);

        BigDecimal totalPrice = orderItemsList.stream()
                .map(orderItems -> orderItems.getDetail().getPrice().multiply(BigDecimal.valueOf(orderItems.getCountDetail())))
                .reduce(BigDecimal.ZERO, BigDecimal::add);

        order.setTotalPrice(totalPrice);

        return orderRepository.save(order);
    }

    public UserEntity getUserByPrinciple(Principal principal) {
        return userRepository.findByUserName(principal.getName()).get();
    }
    public BigDecimal calculateTotalPrice(Order order) {
        return order.getOrderItemsList().stream()
                .map(orderItems -> orderItems.getDetail().getPrice().multiply(BigDecimal.valueOf(orderItems.getCountDetail())))
                .reduce(BigDecimal.ZERO, BigDecimal::add);
    }
}


