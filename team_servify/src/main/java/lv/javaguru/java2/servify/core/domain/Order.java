package lv.javaguru.java2.servify.core.domain;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.Set;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "orders")
public class Order {
    @Id
    @Column(name = "id")
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;
    @Column(name = "user_id")
    private Long userId;
    @Column(name = "order_date")
    private LocalDate orderDate;
    @Column(name = "order_close_date")
    private LocalDate orderComplete;
    @Column(name = "total_price")
    private BigDecimal totalPrice;
    @Column(name = "notes")
    private String notes;
//    @ElementCollection(targetClass = OrderStatus.class, fetch = FetchType.EAGER)
//    @CollectionTable(name = "order_status", joinColumns = @JoinColumn(name = "status_id"))
//    @Enumerated(EnumType.STRING)
//    private Set<OrderStatus> orderStatus;
    @Column(name = "order_status")
    private String orderStatus;
}
