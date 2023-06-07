package shop.core_api.entry_point.customer;

import shop.core_api.requests.customer.BuyRequest;
import shop.core_api.responses.customer.BuyResponse;

public interface BuyService {

    BuyResponse execute(BuyRequest request);

}
