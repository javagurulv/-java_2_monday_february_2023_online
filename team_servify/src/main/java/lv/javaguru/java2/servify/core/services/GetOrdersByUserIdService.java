package lv.javaguru.java2.servify.core.services;

import lv.javaguru.java2.servify.core.database.OrdersRepository;
import lv.javaguru.java2.servify.core.dto.responses.GetOrdersResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class GetOrdersByUserIdService {
    @Autowired
    private OrdersRepository ordersRepository;

    public GetOrdersResponse getOrders(Long id) {
        var orders = ordersRepository.getOrdersByUserId(id);
        return new GetOrdersResponse(orders);
    }
}
