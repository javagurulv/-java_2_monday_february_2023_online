package shop.core.responses.manager;

import lombok.Getter;
import shop.core.responses.CoreError;
import shop.core.responses.CoreResponse;

import java.util.List;

@Getter
public class AddItemToShopResponse extends CoreResponse {

    public AddItemToShopResponse() {
    }

    public AddItemToShopResponse(List<CoreError> errors) {
        super(errors);
    }

}
