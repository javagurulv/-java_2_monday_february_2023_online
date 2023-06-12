package lv.javaguru.java2.servify.core.dto.responses;

import lv.javaguru.java2.servify.core.domain.Detail;

import java.util.List;

public class AddDetailResponse extends CoreResponse {

    private Detail newDetail;
    private boolean isAdded;

    public AddDetailResponse(List<CoreError> errors) {
        super(errors);
    }

    public AddDetailResponse(Detail newDetail) {
        this.newDetail = newDetail;
    }
    public AddDetailResponse(boolean isAdded) {
        this.isAdded = isAdded;
    }
    public Detail newDetail() {
        return newDetail;
    }
}
