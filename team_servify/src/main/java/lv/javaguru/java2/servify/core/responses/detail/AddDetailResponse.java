package lv.javaguru.java2.servify.core.responses.detail;

import lv.javaguru.java2.servify.core.responses.CoreError;
import lv.javaguru.java2.servify.core.responses.CoreResponse;
import lv.javaguru.java2.servify.domain.UserEntity;
import lv.javaguru.java2.servify.domain.detail.Detail;

import java.util.List;

public class AddDetailResponse extends CoreResponse {

    private Detail newDetail;

    public AddDetailResponse(List<CoreError> errors) {
        super(errors);
    }

    public AddDetailResponse(Detail newDetail) {
        this.newDetail = newDetail;
    }

    public Detail newDetail() {
        return newDetail;
    }
}
