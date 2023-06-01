package lv.javaguru.java2.servify.core.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class DetailDTO {
    private Long id;
    private String type;
    private String side;
    private String price;
}
