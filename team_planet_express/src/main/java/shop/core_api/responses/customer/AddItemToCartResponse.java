package shop.core_api.responses.customer;

import lombok.Getter;
import shop.core_api.responses.CoreError;
import shop.core_api.responses.CoreResponse;

import java.util.List;

@Getter
public class AddItemToCartResponse extends CoreResponse {

    public AddItemToCartResponse() {
    }

    public AddItemToCartResponse(List<CoreError> errors) {
        super(errors);
    }

}
