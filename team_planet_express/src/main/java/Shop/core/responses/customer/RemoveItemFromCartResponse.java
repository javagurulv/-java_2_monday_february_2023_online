package Shop.core.responses.customer;

import Shop.core.responses.CoreError;
import Shop.core.responses.CoreResponse;

import java.util.List;

public class RemoveItemFromCartResponse extends CoreResponse {

    public RemoveItemFromCartResponse() {
    }

    public RemoveItemFromCartResponse(List<CoreError> errors) {
        super(errors);
    }

}
