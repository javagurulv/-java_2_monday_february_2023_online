package lv.javaguru.java2.servify.core.domain;

//import javax.persistence.*;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.List;

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
    @ManyToOne(cascade = CascadeType.REFRESH, fetch = FetchType.LAZY)
    @JoinColumn(name = "user_id")
    private UserEntity user;
    @Temporal(TemporalType.TIMESTAMP)
    @Column(name = "order_date")
    private LocalDateTime orderDate;
    @Temporal(TemporalType.TIMESTAMP)
    @Column(name = "order_close_date")
    private LocalDateTime orderComplete;
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
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "order")
    //@JoinColumn(name = "order_id")
    private List<OrderItems> orderItemsList;

    @PrePersist
    public void init() {
        orderDate = LocalDateTime.now();
    }
}
