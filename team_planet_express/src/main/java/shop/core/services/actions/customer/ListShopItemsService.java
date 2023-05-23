package shop.core.services.actions.customer;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import shop.core.database.Repository;
import shop.core.domain.user.UserRole;
import shop.core.requests.customer.ListShopItemsRequest;
import shop.core.responses.customer.ListShopItemsResponse;
import shop.core.services.validators.universal.system.DatabaseAccessValidator;

@Component
public class ListShopItemsService {

    @Autowired
    private Repository repository;
    @Autowired
    private DatabaseAccessValidator databaseAccessValidator;

    public ListShopItemsResponse execute(ListShopItemsRequest request) {
        return new ListShopItemsResponse(repository.accessItemDatabase().getAllItems(),
                UserRole.valueOf(databaseAccessValidator.getUserById(request.getCurrentUser().getUser().getId()).getUserRole()));
    }

}
