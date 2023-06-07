package shop.core_api.responses.customer;

import lombok.Getter;
import shop.core_api.responses.CoreError;
import shop.core_api.responses.CoreResponse;

import java.util.List;

@Getter
public class BuyResponse extends CoreResponse {

    public BuyResponse() {
    }

    public BuyResponse(List<CoreError> errors) {
        super(errors);
    }

}
