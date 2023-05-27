package lv.javaguru.java2.servify.core.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lv.javaguru.java2.servify.core.domain.Address;
import lv.javaguru.java2.servify.core.domain.UserType;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class UserDTO {
    private Long id;
    private String firstName;
    private String lastName;
    private String email;
    private String phoneNumber;
    private Address address;
    private boolean isInactive;
    //private UserType userType;
    private String userType;
}
