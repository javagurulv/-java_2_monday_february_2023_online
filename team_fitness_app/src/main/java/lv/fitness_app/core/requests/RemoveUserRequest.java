package lv.fitness_app.core.requests;

public class RemoveUserRequest {

    private String email;
    private String password;

    public RemoveUserRequest() { }

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

    public void setEmail(String email) {
        this.email = email;
    }

    public void setPassword(String password) {
        this.password = password;
    }
}
