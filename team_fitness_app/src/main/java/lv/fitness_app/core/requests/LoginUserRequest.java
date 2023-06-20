package lv.fitness_app.core.requests;

public class LoginUserRequest {

    private String email;
    private String password;
    public LoginUserRequest() { }

    public LoginUserRequest(String email, String password) {
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
