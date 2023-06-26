package shop.core.converters;

import org.junit.jupiter.api.Test;
import shop.core.domain.Cart;
import shop.core.domain.User;
import shop.core.dtos.CartDto;
import shop.core.enums.CartStatus;

import java.time.LocalDateTime;

import static org.junit.jupiter.api.Assertions.assertEquals;

class CartConverterTest {

    private static final String USER_LOGIN = "userLogin";
    private static final LocalDateTime LAST_UPDATE = LocalDateTime.of(2023, 6, 26, 19, 1);

    private final CartConverter cartConverter = new CartConverter();

    @Test
    void shouldConvert() {
        User user = new User();
        user.setLogin(USER_LOGIN);
        Cart cart = new Cart();
        cart.setUser(user);
        cart.setStatus(CartStatus.OPEN);
        cart.setLastUpdate(LAST_UPDATE);
        CartDto cartDto = cartConverter.toCartDto(cart);
        assertEquals(USER_LOGIN, cartDto.getUserLogin());
        assertEquals(CartStatus.OPEN.toString(), cartDto.getCartStatus());
        assertEquals(LAST_UPDATE, cartDto.getLastUpdate());
    }

}
