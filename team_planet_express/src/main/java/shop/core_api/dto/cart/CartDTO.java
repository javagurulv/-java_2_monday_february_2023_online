package shop.core_api.dto.cart;

import lombok.Data;
import lombok.NoArgsConstructor;
import shop.core.domain.user.User;

import java.time.LocalDateTime;

@Data
@NoArgsConstructor
public class CartDTO {

    private Long id;
    private User user;
    private CartDTOStatus status;
    private LocalDateTime lastUpdate;

    public CartDTO(User user) {
        this.user = user;
        this.status = CartDTOStatus.OPEN;
    }

}
