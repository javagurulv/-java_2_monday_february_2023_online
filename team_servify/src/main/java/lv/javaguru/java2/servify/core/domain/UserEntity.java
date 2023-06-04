package lv.javaguru.java2.servify.core.domain;

//import javax.persistence.*;

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
    @Column(name = "user_name")
    private String userName;
    @Column(name = "phone_number")
    private String phoneNumber;
    @OneToOne
    @JoinTable(name = "user_address")
    @JoinColumn(name = "address_id")
    private Address address;
    @Column(name = "password")
    private String password;
    @Column(name = "active")
    private boolean isActive;
    @ManyToMany
    @JoinTable(name = "user_role", joinColumns = @JoinColumn(name = "user_id"),
            inverseJoinColumns = @JoinColumn(name = "role_id"))
    private Set<UserType> roles;

    public UserEntity(String firstName, String lastName, String email, String phoneNumber, Set<UserType> roles) {
        this.firstName = firstName;
        this.lastName = lastName;
        this.email = email;
        this.userName = email;
        this.phoneNumber = phoneNumber;
        this.setActive(true);
        this.roles = roles;
    }
}
