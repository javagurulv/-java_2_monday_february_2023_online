package shop.core.responses.shared;

import lombok.Getter;
import shop.core.responses.CoreError;
import shop.core.responses.CoreResponse;

import java.util.List;

@Getter
public class SignOutResponse extends CoreResponse {

    public SignOutResponse() {
    }

    public SignOutResponse(List<CoreError> errors) {
        super(errors);
    }

}
