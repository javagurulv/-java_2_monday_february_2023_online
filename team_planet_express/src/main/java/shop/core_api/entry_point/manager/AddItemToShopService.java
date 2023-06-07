package shop.core_api.entry_point.manager;

import shop.core_api.requests.manager.AddItemToShopRequest;
import shop.core_api.responses.manager.AddItemToShopResponse;

public interface AddItemToShopService {
    public AddItemToShopResponse execute(AddItemToShopRequest request);
}
