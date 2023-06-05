package lv.javaguru.java2.servify.core.domain;

//import javax.persistence.*;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import java.math.BigDecimal;
import java.util.Date;
import java.util.Set;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "orders")
public class Order {
    @Id
    @Column(name = "id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @ManyToOne
    @JoinColumn(name = "user_id")
    private UserEntity user;
    @Temporal(TemporalType.TIMESTAMP)
    @Column(name = "order_date")
    private Date orderDate;
    @Temporal(TemporalType.TIMESTAMP)
    @Column(name = "order_close_date")
    private Date orderComplete;
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
