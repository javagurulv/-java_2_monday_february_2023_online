package lv.javaguru.java2.servify.core.services;

//import javax.transaction.Transactional;
import lv.javaguru.java2.servify.core.database.OrdersRepository;
import lv.javaguru.java2.servify.core.database.jpa.JpaOrderRepository;
import lv.javaguru.java2.servify.core.dto.responses.GetAllOrdersResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@Transactional
public class GetAllOrdersService {
    @Autowired
    private JpaOrderRepository ordersRepository;

    public GetAllOrdersResponse getAll() {
        return new GetAllOrdersResponse(ordersRepository.findAll());
    }
}
