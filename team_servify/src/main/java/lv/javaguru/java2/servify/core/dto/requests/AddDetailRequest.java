package lv.javaguru.java2.servify.core.dto.requests;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;

@Data
@NoArgsConstructor
public class AddDetailRequest {

    //private Long id;
    private String detailType;
    private String detailSide;
    private BigDecimal detailPrice;

    public AddDetailRequest(String detailType, String detailSide, BigDecimal detailPrice) {
        this.detailType = detailType;
        this.detailSide = detailSide;
        this.detailPrice = detailPrice;
    }
}
