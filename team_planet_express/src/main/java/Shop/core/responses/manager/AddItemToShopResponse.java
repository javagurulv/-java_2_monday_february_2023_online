package Shop.core.responses.manager;

import Shop.core.responses.CoreError;
import Shop.core.responses.CoreResponse;

import java.util.List;

public class AddItemToShopResponse extends CoreResponse {

    public AddItemToShopResponse() {
    }

    public AddItemToShopResponse(List<CoreError> errors) {
        super(errors);
    }

}
