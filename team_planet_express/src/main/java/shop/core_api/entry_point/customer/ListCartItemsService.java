package shop.core_api.entry_point.customer;

import shop.core_api.requests.customer.ListCartItemsRequest;
import shop.core_api.responses.customer.ListCartItemsResponse;

public interface ListCartItemsService {

    ListCartItemsResponse execute(ListCartItemsRequest request);

}
