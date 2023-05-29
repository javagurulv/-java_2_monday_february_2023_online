package lv.javaguru.java2.servify.core.services;

import lv.javaguru.java2.servify.core.database.OrdersItemsRepository;
import lv.javaguru.java2.servify.core.dto.responses.GetOrdersItemsResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class GetOrderItemsService {
    @Autowired
    private OrdersItemsRepository ordersItemsRepository;

    public GetOrdersItemsResponse getOrderItemsByUserId(Long userId) {
        return new GetOrdersItemsResponse(ordersItemsRepository.getOrdersItemsByUserId(userId));
    }
}
