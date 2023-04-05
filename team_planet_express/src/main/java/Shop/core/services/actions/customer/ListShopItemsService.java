package Shop.core.services.actions.customer;

import Shop.core.database.Database;
import Shop.core.requests.customer.ListShopItemsRequest;
import Shop.core.responses.customer.ListShopItemsResponse;

public class ListShopItemsService {

    private final Database database;

    public ListShopItemsService(Database database) {
        this.database = database;
    }

    public ListShopItemsResponse execute(ListShopItemsRequest request) {
        return new ListShopItemsResponse(database.accessItemDatabase().getAllItems());
    }

}
