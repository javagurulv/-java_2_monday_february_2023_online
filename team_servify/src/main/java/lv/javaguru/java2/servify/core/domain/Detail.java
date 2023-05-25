package lv.javaguru.java2.servify.core.domain;

import lombok.AllArgsConstructor;
import lombok.Data;

import javax.persistence.*;
import java.math.BigDecimal;

@Data
@AllArgsConstructor
@Entity
@Table(name = "details")
public class Detail {
    @Id
    @Column(name = "id")
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;
    @Column(name = "detail_type", nullable = false)
    private String type;
    @Column(name = "detail_side", nullable = false)
    private String side;
    @Column(name = "price", nullable = false)
    private BigDecimal price;

    public Detail() {}

    public Detail(String type, String side, BigDecimal price) {
        this.type = type;
        this.side = side;
        this.price = price;
    }
}