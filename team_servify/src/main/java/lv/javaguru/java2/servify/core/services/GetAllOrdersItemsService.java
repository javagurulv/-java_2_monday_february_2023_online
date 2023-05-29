package lv.javaguru.java2.servify.core.services;

import lv.javaguru.java2.servify.core.database.OrdersItemsRepository;
import lv.javaguru.java2.servify.core.dto.responses.GetAllOrdersItemsResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class GetAllOrdersItemsService {
    @Autowired
    private OrdersItemsRepository ordersItemsRepository;

    public GetAllOrdersItemsResponse getAll() {
        return new GetAllOrdersItemsResponse(ordersItemsRepository.getAll());
    }
}