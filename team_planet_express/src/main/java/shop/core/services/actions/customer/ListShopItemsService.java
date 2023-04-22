package shop.core.services.actions.customer;

import shop.core.database.Database;
import shop.core.requests.customer.ListShopItemsRequest;
import shop.core.responses.customer.ListShopItemsResponse;
import shop.core.services.validators.universal.system.DatabaseAccessValidator;
import shop.dependency_injection.DIComponent;
import shop.dependency_injection.DIDependency;

@DIComponent
public class ListShopItemsService {

    @DIDependency
    private Database database;
    @DIDependency
    private DatabaseAccessValidator databaseAccessValidator;

    public ListShopItemsResponse execute(ListShopItemsRequest request) {
        return new ListShopItemsResponse(database.accessItemDatabase().getAllItems(),
                databaseAccessValidator.getUserById(request.getUserId().getValue()).getUserRole());
    }

}
