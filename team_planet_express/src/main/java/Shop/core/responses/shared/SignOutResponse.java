package Shop.core.responses.shared;

import Shop.core.responses.CoreError;
import Shop.core.responses.CoreResponse;

import java.util.List;

public class SignOutResponse extends CoreResponse {

    public SignOutResponse() {
    }

    public SignOutResponse(List<CoreError> errors) {
        super(errors);
    }

}
