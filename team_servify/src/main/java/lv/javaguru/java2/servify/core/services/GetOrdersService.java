package lv.javaguru.java2.servify.core.services;

import lv.javaguru.java2.servify.core.database.OrdersRepository;
import lv.javaguru.java2.servify.core.domain.UserEntity;
import lv.javaguru.java2.servify.core.dto.responses.GetOrdersResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class GetOrdersService {
    @Autowired
    private OrdersRepository ordersRepository;

    public GetOrdersResponse getOrders(UserEntity user) {
        var orders = ordersRepository.getOrders(user);
        return new GetOrdersResponse(orders);
    }
}
