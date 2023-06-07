package shop.core_api.entry_point.shared;

import shop.core_api.requests.shared.SearchItemRequest;
import shop.core_api.responses.shared.SearchItemResponse;

public interface SearchItemService {
    SearchItemResponse execute(SearchItemRequest request);
}
