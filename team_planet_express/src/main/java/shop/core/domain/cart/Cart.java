package shop.core.domain.cart;

import lombok.*;
import shop.core.domain.user.User;

import javax.persistence.*;
import java.time.LocalDateTime;

@Entity
@Table(name = "cart")
@Getter
@Setter
@RequiredArgsConstructor
@NoArgsConstructor
public class Cart {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne
    @JoinColumn(name = "user_id")
    @NonNull
    private User user;

    @Column(name = "status")
    @Enumerated(EnumType.STRING)
    @NonNull
    private CartStatus status;

    @Column(name = "last_update")
    @NonNull
    private LocalDateTime lastUpdate;

    public long getUserId() {
        return user.getId();
    }

    public Cart(long userId) {
        user = new User();
        user.setId(userId);
        status = CartStatus.OPEN;
        lastUpdate = LocalDateTime.now();
    }
}
