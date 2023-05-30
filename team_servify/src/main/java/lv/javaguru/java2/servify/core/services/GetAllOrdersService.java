package lv.javaguru.java2.servify.core.services;

import jakarta.transaction.Transactional;
import lv.javaguru.java2.servify.core.database.OrdersRepository;
import lv.javaguru.java2.servify.core.dto.responses.GetAllOrdersResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
@Transactional
public class GetAllOrdersService {
    @Autowired
    private OrdersRepository ordersRepository;

    public GetAllOrdersResponse getAll() {
        return new GetAllOrdersResponse(ordersRepository.getAll());
    }
}
