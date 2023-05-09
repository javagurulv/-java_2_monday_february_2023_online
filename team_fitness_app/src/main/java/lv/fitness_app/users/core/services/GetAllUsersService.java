package lv.fitness_app.users.core.services;

import lv.fitness_app.database.Database;
import lv.fitness_app.users.core.requests.GetAllUsersRequest;
import lv.fitness_app.users.core.responses.GetAllUsersResponse;
import lv.fitness_app.users.core.domain.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class GetAllUsersService {

    @Autowired
    @Qualifier("inMemory")
    private Database database;


    public GetAllUsersResponse execute(GetAllUsersRequest request) {
        List<User> users = database.getAllUsers();
        return new GetAllUsersResponse(users);
    }
}
