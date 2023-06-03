package shop.core.domain.item;

import jakarta.persistence.*;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;

@Data
@NoArgsConstructor
@Entity
@Table(name = "item")
public class Item {

    @Id
    @Column(name = "id", nullable = false)
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @Column(name = "name", nullable = false)
    private String name;
    @Column(name = "price", nullable = false)
    private BigDecimal price;
    @Column(name = "available_quantity", nullable = false)
    private Integer availableQuantity;
    @Lob
    @Basic(fetch = FetchType.LAZY)
    @Column(name = "item_picture", nullable = true)
    private byte[] itemPicture;

    public Item(String name, BigDecimal price, Integer availableQuantity) {
        this.name = name;
        this.price = price;
        this.availableQuantity = availableQuantity;
    }

}
