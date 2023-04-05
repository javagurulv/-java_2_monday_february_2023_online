package Shop.core.responses.customer;

import Shop.core.responses.CoreError;
import Shop.core.responses.CoreResponse;

import java.util.List;

public class BuyResponse extends CoreResponse {

    public BuyResponse() {
    }

    public BuyResponse(List<CoreError> errors) {
        super(errors);
    }

}
