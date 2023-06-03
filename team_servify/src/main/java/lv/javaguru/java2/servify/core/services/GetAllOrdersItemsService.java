package lv.javaguru.java2.servify.core.services;

//import javax.transaction.Transactional;
import lv.javaguru.java2.servify.core.database.OrdersItemsRepository;
import lv.javaguru.java2.servify.core.database.jpa.JpaOrderItemRepository;
import lv.javaguru.java2.servify.core.dto.responses.GetAllOrdersItemsResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@Transactional
public class GetAllOrdersItemsService {
    @Autowired
    private JpaOrderItemRepository ordersItemsRepository;

    public GetAllOrdersItemsResponse getAll() {
        return new GetAllOrdersItemsResponse(ordersItemsRepository.findAll());
    }
}
