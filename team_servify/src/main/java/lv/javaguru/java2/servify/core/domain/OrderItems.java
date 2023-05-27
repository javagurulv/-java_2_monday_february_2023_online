package lv.javaguru.java2.servify.core.domain;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor

//@Entity
//@Table(name = "orders_items")
public class OrderItems {
    @Id
    @Column(name = "id")
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;
    @ManyToOne
    @JoinColumn(name = "order_id", nullable = false)
    private Order order;
    @ManyToOne
    @JoinColumn(name = "detail_id", nullable = false)
    private Detail detail;
    @ManyToOne
    @JoinColumn(name = "color_id", nullable = false)
    private Color color;
    @Column(name = "count")
    private Integer countDetail;

    public OrderItems() {

    }
}
