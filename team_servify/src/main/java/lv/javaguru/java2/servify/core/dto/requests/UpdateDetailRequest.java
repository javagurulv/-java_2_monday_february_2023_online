package lv.javaguru.java2.servify.core.dto.requests;

import lombok.Data;

@Data
public class UpdateDetailRequest {
    private Long id;
    private String type;
    private String side;
    private String price;
}
