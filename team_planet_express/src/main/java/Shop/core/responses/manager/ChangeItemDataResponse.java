package Shop.core.responses.manager;

import Shop.core.responses.CoreError;
import Shop.core.responses.CoreResponse;

import java.util.List;

public class ChangeItemDataResponse extends CoreResponse {

    public ChangeItemDataResponse() {
    }

    public ChangeItemDataResponse(List<CoreError> errors) {
        super(errors);
    }

}
