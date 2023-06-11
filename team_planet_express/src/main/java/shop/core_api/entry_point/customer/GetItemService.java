package shop.core_api.entry_point.customer;

import shop.core_api.requests.customer.GetItemRequest;
import shop.core_api.responses.customer.GetItemResponse;

public interface GetItemService {
    GetItemResponse execute(GetItemRequest request);
}
