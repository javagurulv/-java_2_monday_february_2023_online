package shop.core_api.entry_point.manager;

import shop.core_api.requests.manager.ChangeItemDataRequest;
import shop.core_api.responses.manager.ChangeItemDataResponse;

public interface ChangeItemDataService {
    ChangeItemDataResponse execute(ChangeItemDataRequest request);
}
