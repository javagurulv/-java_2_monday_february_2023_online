package lv.javaguru.java2.servify.domain;

import java.math.BigDecimal;
import java.math.BigInteger;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

public class Order {
    private Long orderId;
    private Long userId; // so user id or client id? Only client makes requests. Manager just approves...
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

    public Long getOrderId() {
        return orderId;
    }

    public void setOrderId(Long orderId) {
        this.orderId = orderId;
    }

    public Long getUserId() {
        return userId;
    }

    public void setUserId(Long userId) {
        this.userId = userId;
    }

    public List<Detail> getDetailList() {
        return detailList;
    }

    public void setDetailList(List<Detail> detailList) {
        this.detailList = detailList;
    }

    public LocalDate getOrderDate() {
        return orderDate;
    }

    public void setOrderDate(LocalDate orderDate) {
        this.orderDate = orderDate;
    }

    public OrderStatus getOrderStatus() {
        return orderStatus;
    }

    public void setOrderStatus(OrderStatus orderStatus) {
        this.orderStatus = orderStatus;
    }

    public BigDecimal getTotalPrice() {
        return totalPrice;
    }

    public void setTotalPrice(BigDecimal totalPrice) {
        this.totalPrice = totalPrice;
    }

    public String getNotes() {
        return notes;
    }

    public void setNotes(String notes) {
        this.notes = notes;
    }

    @Override
    public String toString() {
        return "Order{" +
                "orderId=" + orderId +
                ", userId=" + userId +
                ", detailList=" + detailList +
                ", orderDate=" + orderDate +
                ", orderStatus=" + orderStatus +
                ", totalPrice=" + totalPrice +
                ", notes='" + notes + '\'' +
                '}';
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Order order)) return false;
        return orderId.equals(order.orderId) && userId.equals(order.userId) && Objects.equals(detailList, order.detailList) && orderDate.equals(order.orderDate) && orderStatus == order.orderStatus && totalPrice.equals(order.totalPrice) && Objects.equals(notes, order.notes);
    }

    @Override
    public int hashCode() {
        return Objects.hash(orderId, userId, detailList, orderDate, orderStatus, totalPrice, notes);
    }
}
