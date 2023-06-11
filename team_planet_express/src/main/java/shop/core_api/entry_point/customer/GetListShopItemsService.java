package shop.core_api.entry_point.customer;

import shop.core_api.requests.customer.GetListShopItemsRequest;
import shop.core_api.responses.customer.GetListShopItemsResponse;


public interface GetListShopItemsService {

    public GetListShopItemsResponse execute(GetListShopItemsRequest request);

}
