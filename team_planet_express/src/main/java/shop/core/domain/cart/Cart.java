package shop.core.domain.cart;

import lombok.Data;

import java.time.LocalDateTime;

@Data
public class Cart {

    private Long id;
    private final Long userId;
    private CartStatus cartStatus;
    private LocalDateTime lastUpdate;

    public Cart(long userId) {
        this.userId = userId;
        this.cartStatus = CartStatus.OPEN;
    }

}
