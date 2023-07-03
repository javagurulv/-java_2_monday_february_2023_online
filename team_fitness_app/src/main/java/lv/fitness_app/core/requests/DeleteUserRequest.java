package lv.fitness_app.core.requests;

public class DeleteUserRequest {

    private String email;

    public DeleteUserRequest() { }

    public DeleteUserRequest(String email) {
        this.email = email;
    }

    public String getEmail() {
        return email;
    }
}
