package lv.javaguru.java2.servify.domain.detail;

import lombok.AllArgsConstructor;
import lombok.Data;
import java.math.BigDecimal;

@Data
@AllArgsConstructor
public class Detail {
    private Long id;
    private DetailTypeEnum type;
    private DetailSideEnum side;
    private BigDecimal price;

    public Detail() {}

    public Detail(DetailTypeEnum type, DetailSideEnum side, BigDecimal price) {
        this.type = type;
        this.side = side;
        this.price = price;
    }
}