package lv.javaguru.java2.servify.core.requests.detail;

import lombok.AllArgsConstructor;
import lombok.Data;

import java.math.BigDecimal;

@Data
@AllArgsConstructor
public class AddDetailRequest {

    private Long id;
    private String detailType;
    private String detailSide;
    private BigDecimal detailPrice;

    public AddDetailRequest(String detailType, String detailSide, BigDecimal detailPrice) {
        this.detailType = detailType;
        this.detailSide = detailSide;
        this.detailPrice = detailPrice;
    }
}
