package lv.javaguru.java2.servify.core.requests;

import lombok.AllArgsConstructor;
import lombok.Data;

import java.math.BigDecimal;
@Data
@AllArgsConstructor
public class SearchDetailRequest {
    private String detailType;
    private String detailSide;
    private BigDecimal detailPrice;

    public boolean isTypeProvided() {
        return this.detailType != null && !this.detailType.isEmpty();
    }

    public boolean isSideProvided() {
        return this.detailSide != null && !this.detailSide.isEmpty();
    }

    public boolean isPriceProvided() {
        return this.detailPrice != null;
    }
}
