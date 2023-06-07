package shop.core_api.responses.manager;

import lombok.Getter;
import shop.core_api.responses.CoreError;
import shop.core_api.responses.CoreResponse;

import java.util.List;

@Getter
public class AddItemToShopResponse extends CoreResponse {

    public AddItemToShopResponse() {
    }

    public AddItemToShopResponse(List<CoreError> errors) {
        super(errors);
    }

}
