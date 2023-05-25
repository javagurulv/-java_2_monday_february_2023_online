package shop.core.domain.user;

import jakarta.persistence.*;
import lombok.*;

@Entity
@Table(name = "user")
@Getter
@Setter
@RequiredArgsConstructor
@NoArgsConstructor
public class User {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @NonNull
    private String name;
    @NonNull
    private String login;
    @NonNull
    private String password;
    @Column(name = "role")
    @Enumerated(EnumType.STRING)
    @NonNull
    private UserRole userRole;

}
