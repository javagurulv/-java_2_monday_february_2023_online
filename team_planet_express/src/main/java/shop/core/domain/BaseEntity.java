package shop.core.domain;

import jakarta.persistence.*;
import lombok.Data;

import java.io.Serializable;

@Data
@MappedSuperclass
public class BaseEntity implements Serializable {
    //    @Id
//    @GeneratedValue
//    @Column(columnDefinition = "BINARY(16)", updatable = false, nullable = false)
//    private UUID id;
    @Id
    @Column(name = "id", nullable = false)
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
}
