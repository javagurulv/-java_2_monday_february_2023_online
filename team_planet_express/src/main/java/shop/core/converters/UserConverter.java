package shop.core.converters;

import org.springframework.stereotype.Component;
import shop.core.domain.User;
import shop.core.dtos.UserDto;

@Component
public class UserConverter {

    public UserDto toUserDto(User user) {
        return new UserDto(
                user.getName(),
                user.getLogin(),
                user.getPassword(),
                user.getUserRole().toString());
    }

}
