package shop.core_api.dto.user;

import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
public class UserDTO {

    private Long id;
    private String name;
    private String login;
    private String password;
    private UserRoleDTO userRoleDTO;

    public UserDTO(String name, String login, String password, UserRoleDTO userRoleDTO) {
        this.name = name;
        this.login = login;
        this.password = password;
        this.userRoleDTO = userRoleDTO;
    }

}
