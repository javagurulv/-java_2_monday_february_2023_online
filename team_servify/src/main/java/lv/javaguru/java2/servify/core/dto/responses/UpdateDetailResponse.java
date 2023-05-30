package lv.javaguru.java2.servify.core.dto.responses;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lv.javaguru.java2.servify.core.domain.Detail;

import java.util.List;


@NoArgsConstructor
public class UpdateDetailResponse extends CoreResponse {
    private Detail updatedDetail;

    public UpdateDetailResponse(Detail updatedDetail) {
        this.updatedDetail = updatedDetail;
    }

    public UpdateDetailResponse(List<CoreError> errors) {
        super(errors);
    }
}
