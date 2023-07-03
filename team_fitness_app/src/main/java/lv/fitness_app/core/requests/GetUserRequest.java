package lv.fitness_app.core.requests;

public class GetUserRequest {

    private String email;

    public GetUserRequest() { }

    public GetUserRequest(String email) {
        this.email = email;
    }

    public String getEmail() {
        return email;
    }
}
