package lv.javaguru.java2.servify.domain;

import lombok.Data;
import lv.javaguru.java2.servify.domain.detail.Detail;
import java.math.BigDecimal;
import java.math.BigInteger;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

@Data
public class Order {
    private Long orderId;
    private final Long userId; // so user id or client id? Only client makes requests. Manager just approves...
    private List<Detail> detailList;
    private LocalDate orderDate;
    private OrderStatus orderStatus;
    private BigDecimal totalPrice;
    private String notes;

    public Order(Long userId) {
        this.userId = userId;
        this.detailList = new ArrayList<>();
        this.orderStatus = OrderStatus.NEW;
        this.orderDate = LocalDate.now();
        this.totalPrice = new BigDecimal(BigInteger.ZERO);
    }
}
