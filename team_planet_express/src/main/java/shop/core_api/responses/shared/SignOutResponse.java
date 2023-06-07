package shop.core_api.responses.shared;

import lombok.Getter;
import shop.core_api.responses.CoreError;
import shop.core_api.responses.CoreResponse;

import java.util.List;

@Getter
public class SignOutResponse extends CoreResponse {

    public SignOutResponse() {
    }

    public SignOutResponse(List<CoreError> errors) {
        super(errors);
    }

}
