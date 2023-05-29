package lv.javaguru.java2.servify.core.domain;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "colors")
public class Color {
    @Id
    @Column(name = "id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @Column(name = "color_code")
    private String oemCode;
    @Column(name = "color_name")
    private String description;
    @Column(name = "is_metalic")
    private boolean isMetalic;

}
