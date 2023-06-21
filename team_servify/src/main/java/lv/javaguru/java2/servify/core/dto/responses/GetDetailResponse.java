package lv.javaguru.java2.servify.core.dto.responses;

import lombok.NoArgsConstructor;
import lv.javaguru.java2.servify.core.dto.DetailDTO;
import java.util.List;

@NoArgsConstructor
public class GetDetailResponse extends CoreResponse {
    private DetailDTO detailDTO;

    public GetDetailResponse(List<CoreError> errors) {
        super(errors);
    }

    public GetDetailResponse(DetailDTO detailDTO) {
        this.detailDTO = detailDTO;
    }

    public DetailDTO getDetailDTO() {
        return detailDTO;
    }
}
