package shop.core.domain.user;

import shop.core_api.dto.user.UserDTO;
import shop.core_api.dto.user.UserRoleDTO;

public class UserConverter {

    public static User toUser(UserDTO userDTO) {
        User user = new User();
        user.setId(userDTO.getId());
        user.setName(userDTO.getName());
        user.setLogin(userDTO.getLogin());
        user.setUserRole(UserRole.valueOf(userDTO.getUserRoleDTO().name()));
        return user;
    }

    public static UserDTO toUserDTO(User user) {
        UserDTO userDTO = new UserDTO(
                user.getId(),
                user.getName(),
                user.getLogin(),
                UserRoleDTO.valueOf(user.getUserRole().name())
        );
        return userDTO;
    }
}
