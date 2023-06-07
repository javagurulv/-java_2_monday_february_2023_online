package shop.core.services.actions.customer;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import shop.core.database.ItemRepository;
import shop.core.services.validators.universal.system.DatabaseAccessValidator;
import shop.core_api.entry_point.customer.ListShopItemsService;
import shop.core_api.requests.customer.ListShopItemsRequest;
import shop.core_api.responses.customer.ListShopItemsResponse;

@Service
@Transactional
public class ListShopItemsServiceImpl implements ListShopItemsService {

    @Autowired
    private ItemRepository itemRepository;
    @Autowired
    private DatabaseAccessValidator databaseAccessValidator;

    public ListShopItemsResponse execute(ListShopItemsRequest request) {
        return new ListShopItemsResponse(itemRepository.getAllItems());
    }

}
