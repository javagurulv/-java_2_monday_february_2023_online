package shop.core_api.responses.customer;

import lombok.Getter;
import shop.core_api.responses.CoreError;
import shop.core_api.responses.CoreResponse;

import java.util.List;

@Getter
public class RemoveItemFromCartResponse extends CoreResponse {

    public RemoveItemFromCartResponse() {
    }

    public RemoveItemFromCartResponse(List<CoreError> errors) {
        super(errors);
    }

}
