package shop.core_api.entry_point.customer;

import shop.core_api.requests.customer.RemoveItemFromCartRequest;
import shop.core_api.responses.customer.RemoveItemFromCartResponse;

public interface RemoveItemFromCartService {

    RemoveItemFromCartResponse execute(RemoveItemFromCartRequest request);

}
