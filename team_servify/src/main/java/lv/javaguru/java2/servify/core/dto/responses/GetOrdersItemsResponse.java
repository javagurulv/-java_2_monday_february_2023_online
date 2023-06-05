package lv.javaguru.java2.servify.core.dto.responses;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lv.javaguru.java2.servify.core.domain.OrderItems;
import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class GetOrdersItemsResponse {
    private List<OrderItems> orderItems;
}
