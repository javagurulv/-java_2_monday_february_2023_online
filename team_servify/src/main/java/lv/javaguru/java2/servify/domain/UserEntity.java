package lv.javaguru.java2.servify.domain;

import lombok.Data;

@Data
public class UserEntity {

    private Long id;
    private String firstName;
    private String lastName;
    private String email;
    private String phoneNumber;
    private UserType userType;
    private Address address;
    private boolean isInactive;
    private String password;


    public UserEntity(String firstName, String lastName, String email, String phoneNumber) {
        this.firstName = firstName;
        this.lastName = lastName;
        this.email = email;
        this.phoneNumber = phoneNumber;
        isInactive = true;
        this.userType = UserType.ANONYMOUS;
    }

    public UserEntity() {
        isInactive = true;
        this.userType = UserType.ANONYMOUS;
    }

}
