package shop.core.responses.manager;

import lombok.Getter;
import shop.core.responses.CoreError;
import shop.core.responses.CoreResponse;

import java.util.List;

@Getter
public class ChangeItemDataResponse extends CoreResponse {

    public ChangeItemDataResponse() {
    }

    public ChangeItemDataResponse(List<CoreError> errors) {
        super(errors);
    }

}
