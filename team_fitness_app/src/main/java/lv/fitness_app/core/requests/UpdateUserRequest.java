package lv.fitness_app.core.requests;

public class UpdateUserRequest {

    private String email;
    private String newUsername;
    private String newPassword;

    public UpdateUserRequest() { }

    public String getEmail() {
        return email;
    }

    public String getNewUsername() {
        return newUsername;
    }

    public String getNewPassword() {
        return newPassword;
    }
}
