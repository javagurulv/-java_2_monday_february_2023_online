package lv.fitness_app.users.core.requests;

public class RemoveUserRequest {

    private String email;
    private String password;

    public RemoveUserRequest(String email, String password) {
        this.email = email;
        this.password = password;
    }

    public String getEmail() {
        return email;
    }

    public String getPassword() {
        return password;
    }
}
