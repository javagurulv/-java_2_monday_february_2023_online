package shop.core.responses.customer;

import lombok.Getter;
import shop.core.responses.CoreError;
import shop.core.responses.CoreResponse;

import java.util.List;

@Getter
public class BuyResponse extends CoreResponse {

    public BuyResponse() {
    }

    public BuyResponse(List<CoreError> errors) {
        super(errors);
    }

}
