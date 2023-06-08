package shop.core.domain.user;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import shop.core.domain.BaseEntity;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "shop_user")
public class User extends BaseEntity {

    @Column(name = "name", nullable = false)
    private String name;
    @Column(name = "login", nullable = false)
    private String login;
    @Column(name = "password", nullable = false)
    private String password;
    @Column(name = "role", nullable = false)
    @Enumerated(EnumType.STRING)
    private UserRole userRole;

}
