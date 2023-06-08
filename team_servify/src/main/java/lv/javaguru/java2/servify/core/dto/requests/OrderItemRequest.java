package lv.javaguru.java2.servify.core.dto.requests;


import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;


@Data
@NoArgsConstructor
@AllArgsConstructor
public class OrderItemRequest {
    private Long id;
    private Long orderId;
    private Long detailId;
    private Long colorId;
    private Long count;
}
