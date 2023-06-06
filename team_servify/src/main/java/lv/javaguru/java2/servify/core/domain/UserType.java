package lv.javaguru.java2.servify.core.domain;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.security.core.GrantedAuthority;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "roles")
public class UserType implements GrantedAuthority {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Long id;
    @Column(name = "role")
    private String authority;

    public UserType(String authority) {
        this.authority = authority;
    }

    @Override
    public String getAuthority() {
        return this.authority;
    }
}
