package lv.javaguru.java2.servify.core.dto.responses;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lv.javaguru.java2.servify.core.domain.Order;
import java.util.List;
@Data
@AllArgsConstructor
@NoArgsConstructor
public class GetOrdersResponse {
    private List<Order> orders;
}
