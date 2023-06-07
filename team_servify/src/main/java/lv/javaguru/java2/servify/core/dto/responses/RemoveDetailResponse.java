package lv.javaguru.java2.servify.core.dto.responses;

import java.util.List;

public class RemoveDetailResponse extends CoreResponse {
    private boolean detailRemoved;

    public RemoveDetailResponse() {
    }

    public RemoveDetailResponse(List<CoreError> errors) {
        super(errors);
    }

    public boolean isDetailRemoved() {
        return detailRemoved;
    }
}
