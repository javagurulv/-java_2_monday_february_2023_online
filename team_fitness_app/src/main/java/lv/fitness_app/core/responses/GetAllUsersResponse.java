package lv.fitness_app.core.responses;

import lv.fitness_app.core.domain.User;

import java.util.List;

public class GetAllUsersResponse extends CoreResponse{

    private List<User> users;

    public GetAllUsersResponse(List<User> users) {
        this.users = users;
    }

    public List<User> getUsers() {
        return users;
    }

}
