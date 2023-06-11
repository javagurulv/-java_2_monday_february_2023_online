package shop.core_api.entry_point.customer;

import shop.core_api.requests.customer.GetListCartItemsRequest;
import shop.core_api.responses.customer.GetListCartItemsResponse;

public interface GetListCartItemsService {

    GetListCartItemsResponse execute(GetListCartItemsRequest request);

}
