package lv.javaguru.java2.servify.core.domain;

//import javax.persistence.*;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;
import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "details")
public class Detail {
    @Id
    @Column(name = "id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @Column(name = "detail_type", nullable = false)
    private String type;
    @Column(name = "detail_side", nullable = false)
    private String side;
    @Column(name = "price", nullable = false)
    private BigDecimal price;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "detail")
    //@JoinColumn(name = "detail_id")
    private List<OrderItems> orderItemsList;

    public Detail(String type, String side, BigDecimal price) {
        this.type = type;
        this.side = side;
        this.price = price;
    }
}