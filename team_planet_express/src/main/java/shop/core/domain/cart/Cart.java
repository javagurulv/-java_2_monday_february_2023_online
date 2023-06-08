package shop.core.domain.cart;

import jakarta.persistence.*;
import lombok.Data;
import lombok.NoArgsConstructor;
import shop.core.domain.BaseEntity;
import shop.core.domain.user.User;

import java.time.LocalDateTime;

@Data
@NoArgsConstructor
@Entity
@Table(name = "cart")
public class Cart extends BaseEntity {

    @ManyToOne
    @JoinColumn(name = "user_id", nullable = false)
    private User user;
    @Column(name = "status", nullable = false)
    @Enumerated(EnumType.STRING)
    private CartStatus status;
    @Column(name = "last_update")
    private LocalDateTime lastUpdate;

    public Cart(User user) {
        this.user = user;
        this.status = CartStatus.OPEN;
    }

    @SuppressWarnings("unused")
    @PrePersist
    @PreUpdate
    private void onUpdate() {
        this.lastUpdate = LocalDateTime.now();
    }


}
