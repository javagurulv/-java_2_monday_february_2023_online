package lv.javaguru.java2.servify.core.dto.responses;

import lv.javaguru.java2.servify.core.domain.Detail;

import java.util.List;


public class SearchDetailResponse extends CoreResponse {
    private List<Detail> details;

    public SearchDetailResponse( List<Detail> details, List<CoreError> errors) {
        super(errors);
        this.details = details;
    }

    public List<Detail> getDetails() {
        return details;
    }
}
