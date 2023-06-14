package lv.javaguru.java2.servify.core.domain;

//import javax.persistence.*;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;


import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import java.util.Set;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "users")
public class UserEntity implements UserDetails {
    @Id
    @Column(name = "id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @Column(name = "first_name")
    private String firstName;
    @Column(name = "last_name")
    private String lastName;
    @Column(name = "email", unique = true)
    private String email;
    @Column(name = "user_name", unique = true)
    private String userName;
    @Column(name = "phone_number", unique = true)
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
    private Set<UserType> authorities;
    @OneToMany(cascade = CascadeType.ALL, fetch = FetchType.EAGER, mappedBy = "user")
    private List<Order> orders = new ArrayList<>();

    public UserEntity(String firstName, String lastName, String email, String phoneNumber, String password, Set<UserType> authorities) {
        this.firstName = firstName;
        this.lastName = lastName;
        this.email = email;
        this.userName = email;
        this.phoneNumber = phoneNumber;
        this.password = password;
        this.isActive = true;
        this.authorities = authorities;
    }
    public UserEntity(String email, String password, Set<UserType> authorities) {
        this.email = email;
        this.userName = email;
        this.password = password;
        this.authorities = authorities;
        this.isActive = true;
    }

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        return this.authorities;
    }

    @Override
    public String getUsername() {
        return this.userName;
    }

    @Override
    public boolean isAccountNonExpired() {
        return true;
    }

    @Override
    public boolean isAccountNonLocked() {
        return true;
    }

    @Override
    public boolean isCredentialsNonExpired() {
        return true;
    }

    @Override
    public boolean isEnabled() {
        return true;
    }
}
