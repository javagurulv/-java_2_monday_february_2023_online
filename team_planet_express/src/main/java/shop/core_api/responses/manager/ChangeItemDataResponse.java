package shop.core_api.responses.manager;

import lombok.Getter;
import shop.core_api.responses.CoreError;
import shop.core_api.responses.CoreResponse;

import java.util.List;

@Getter
public class ChangeItemDataResponse extends CoreResponse {

    public ChangeItemDataResponse() {
    }

    public ChangeItemDataResponse(List<CoreError> errors) {
        super(errors);
    }

}
