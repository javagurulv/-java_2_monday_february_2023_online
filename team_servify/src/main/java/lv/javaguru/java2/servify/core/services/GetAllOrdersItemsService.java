package lv.javaguru.java2.servify.core.services;

//import javax.transaction.Transactional;
import lv.javaguru.java2.servify.core.database.jpa.JpaOrderItemsRepository;
import lv.javaguru.java2.servify.core.dto.responses.GetAllOrdersItemsResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@Transactional
public class GetAllOrdersItemsService {
    @Autowired
    private JpaOrderItemsRepository ordersItemsRepository;

    public GetAllOrdersItemsResponse getAll() {
        return new GetAllOrdersItemsResponse(ordersItemsRepository.findAll());
    }
}
