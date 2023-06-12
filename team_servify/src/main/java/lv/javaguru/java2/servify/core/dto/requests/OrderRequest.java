package lv.javaguru.java2.servify.core.dto.requests;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.security.Principal;
import java.util.List;
@Data
@NoArgsConstructor
@AllArgsConstructor
public class OrderRequest {
    private Principal principal;
    private List<OrderItemRequest> orderItemRequests;
    private String notes;
    private String orderStatus;

}

