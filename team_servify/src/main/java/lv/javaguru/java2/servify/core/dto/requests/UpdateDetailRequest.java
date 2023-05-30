package lv.javaguru.java2.servify.core.dto.requests;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class UpdateDetailRequest {
    private Long id;
    private String type;
    private String side;
    private BigDecimal price;
}
