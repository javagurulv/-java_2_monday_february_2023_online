package shop.core_api.dto.user;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import shop.core.domain.user.User;
import shop.core.domain.user.UserRole;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class UserDTO {

    private Long id;
    private String name;
    private String login;
    private UserRoleDTO userRoleDTO;

    public User toUser() {
        return UserDTO.toUser(this);
    }

    public static User toUser(UserDTO userDTO) {
        User user = new User();
        user.setId(userDTO.getId());
        user.setName(userDTO.getName());
        user.setLogin(userDTO.getLogin());
        user.setUserRole(UserRole.valueOf(userDTO.getUserRoleDTO().name()));
        return user;
    }

    public static UserDTO of(User user) {
        UserDTO userDTO = new UserDTO(
                user.getId(),
                user.getName(),
                user.getLogin(),
                UserRoleDTO.valueOf(user.getUserRole().name())
        );
        return userDTO;
    }

}
