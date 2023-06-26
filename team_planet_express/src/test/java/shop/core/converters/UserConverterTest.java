package shop.core.converters;

import org.junit.jupiter.api.Test;
import shop.core.domain.User;
import shop.core.dtos.UserDto;
import shop.core.enums.UserRole;

import static org.junit.jupiter.api.Assertions.assertEquals;

class UserConverterTest {

    private static final String NAME = "name";
    private static final String LOGIN = "login";
    private static final String PASSWORD = "password";

    private final UserConverter userConverter = new UserConverter();

    @Test
    void shouldConvert() {
        User user = new User();
        user.setName(NAME);
        user.setLogin(LOGIN);
        user.setPassword(PASSWORD);
        user.setUserRole(UserRole.CUSTOMER);
        UserDto userDto = userConverter.toUserDto(user);
        assertEquals(NAME, userDto.getName());
        assertEquals(LOGIN, userDto.getLogin());
        assertEquals(PASSWORD, userDto.getPassword());
        assertEquals(UserRole.CUSTOMER.toString(), userDto.getUserRole());
    }

}
