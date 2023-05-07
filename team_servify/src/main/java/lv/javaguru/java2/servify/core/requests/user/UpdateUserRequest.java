package lv.javaguru.java2.servify.core.requests.user;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Value;

@Value
@AllArgsConstructor
@Getter
public class UpdateUserRequest {
    String firstName;
    String lastName;
    String email;
    String phoneNumber;

}
