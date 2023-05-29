package lv.javaguru.java2.servify.core.domain;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import java.util.Set;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "users")
public class UserEntity {
    @Id
    @Column(name = "id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @Column(name = "first_name")
    private String firstName;
    @Column(name = "last_name")
    private String lastName;
    @Column(name = "email")
    private String email;
    @Column(name = "phone_number")
    private String phoneNumber;
    @OneToOne
    @JoinColumn(name = "address_id")
    private Address address;
    @Column(name = "password")
    private String password;
    @Column(name = "is_inactive")
    private boolean isInactive;
    @Column(name = "user_type")
    private String userType;
//    @ElementCollection(targetClass = UserType.class, fetch = FetchType.EAGER)
//    @CollectionTable(name = "role", joinColumns = @JoinColumn(name = "id"))
//    @Enumerated(EnumType.STRING)
//    private Set<UserType> userType;

    public UserEntity(String firstName, String lastName, String email, String phoneNumber) {
        this.firstName = firstName;
        this.lastName = lastName;
        this.email = email;
        this.phoneNumber = phoneNumber;
        this.isInactive = false;
        this.userType = "ANONYMOUS";
    }
}
