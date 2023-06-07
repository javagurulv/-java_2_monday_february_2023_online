package shop.core_api.entry_point.customer;

import shop.core_api.requests.customer.AddItemToCartRequest;
import shop.core_api.responses.customer.AddItemToCartResponse;

public interface AddItemToCartService {

    AddItemToCartResponse execute(AddItemToCartRequest request);

}
