package shop.core.services.actions.customer;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import shop.core.database.ItemRepository;
import shop.core_api.entry_point.customer.GetListShopItemsService;
import shop.core_api.requests.customer.GetListShopItemsRequest;
import shop.core_api.responses.customer.GetListShopItemsResponse;

@Service
@Transactional
public class GetListShopItemsServiceImpl implements GetListShopItemsService {

    @Autowired
    private ItemRepository itemRepository;

    public GetListShopItemsResponse execute(GetListShopItemsRequest request) {
        return new GetListShopItemsResponse(itemRepository.findAll());
    }

}
