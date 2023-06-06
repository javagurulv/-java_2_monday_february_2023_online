package lv.javaguru.java2.servify.core.domain;

//import javax.persistence.*;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;


@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "addresses")
public class Address {
    @Id
    @Column(name = "id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @Column(name = "country")
    private String country;
    @Column(name = "city")
    private String city;
    @Column(name = "street")
    private String street;
    @Column(name = "house_number")
    private String homeNumber;
    @Column(name = "apartment_number")
    private String apartmentNumber;
    @Column(name = "postal_code")
    private String postalCode;

}
