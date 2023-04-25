package shop.core.services.actions.customer;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import shop.core.database.Database;
import shop.core.requests.customer.ListShopItemsRequest;
import shop.core.responses.customer.ListShopItemsResponse;
import shop.core.services.validators.universal.system.DatabaseAccessValidator;

@Component
public class ListShopItemsService {

    @Autowired
    private Database database;
    @Autowired
    private DatabaseAccessValidator databaseAccessValidator;

    public ListShopItemsResponse execute(ListShopItemsRequest request) {
        return new ListShopItemsResponse(database.accessItemDatabase().getAllItems(),
                databaseAccessValidator.getUserById(request.getUserId().getValue()).getUserRole());
    }

}
