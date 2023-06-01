package lv.javaguru.java2.servify.core.dto.requests;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lv.javaguru.java2.servify.core.domain.Address;
@Data
@AllArgsConstructor
@NoArgsConstructor
public class AddUserRequest {
    private Long id;
    private String firstName;
    private String lastName;
    private String email;
    private String phoneNumber;
    private Address address;
    private boolean isInactive;
    private String userType;

    public AddUserRequest(String firstName, String lastName, String email, String phoneNumber) {
        this.firstName = firstName;
        this.lastName = lastName;
        this.email = email;
        this.phoneNumber = phoneNumber;
        this.isInactive = false;
        this.userType = "ANONYMOUS";
    }
}
