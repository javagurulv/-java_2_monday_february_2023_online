package Shop.core.responses.customer;

import Shop.core.responses.CoreError;
import Shop.core.responses.CoreResponse;

import java.util.List;

public class AddItemToCartResponse extends CoreResponse {

    public AddItemToCartResponse() {
    }

    public AddItemToCartResponse(List<CoreError> errors) {
        super(errors);
    }

}
