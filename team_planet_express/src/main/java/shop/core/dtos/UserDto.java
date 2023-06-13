package shop.core.dtos;

import lombok.Value;

@Value
public class UserDto {

    String name;
    String login;
    String password;
    String userRole;

}
