package shop.core.services.actions.customer;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;
import shop.core.database.ItemRepository;
import shop.core.requests.customer.ListShopItemsRequest;
import shop.core.responses.customer.ListShopItemsResponse;
import shop.core.services.validators.universal.system.DatabaseAccessValidator;

@Component
@Transactional
public class ListShopItemsService {

    @Autowired
    private ItemRepository itemRepository;
    @Autowired
    private DatabaseAccessValidator databaseAccessValidator;

    public ListShopItemsResponse execute(ListShopItemsRequest request) {
        return new ListShopItemsResponse(itemRepository.getAllItems());
    }

}
