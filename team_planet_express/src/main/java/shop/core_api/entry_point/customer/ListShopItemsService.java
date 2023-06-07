package shop.core_api.entry_point.customer;

import shop.core_api.requests.customer.ListShopItemsRequest;
import shop.core_api.responses.customer.ListShopItemsResponse;


public interface ListShopItemsService {

    public ListShopItemsResponse execute(ListShopItemsRequest request);

}
