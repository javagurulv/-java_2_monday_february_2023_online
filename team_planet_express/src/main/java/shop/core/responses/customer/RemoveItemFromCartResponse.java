package shop.core.responses.customer;

import lombok.Getter;
import shop.core.responses.CoreError;
import shop.core.responses.CoreResponse;

import java.util.List;

@Getter
public class RemoveItemFromCartResponse extends CoreResponse {

    public RemoveItemFromCartResponse() {
    }

    public RemoveItemFromCartResponse(List<CoreError> errors) {
        super(errors);
    }

}
