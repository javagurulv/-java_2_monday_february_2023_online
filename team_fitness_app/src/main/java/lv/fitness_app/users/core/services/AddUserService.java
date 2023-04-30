package lv.fitness_app.users.core.services;

import lv.fitness_app.users.core.database.Database;
import lv.fitness_app.users.core.requests.AddUserRequest;
import lv.fitness_app.users.core.responses.AddUserResponse;
import lv.fitness_app.users.core.responses.CoreError;
import lv.fitness_app.users.core.domain.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class AddUserService {

    @Autowired private Database database;
    @Autowired private AddUserRequestValidator validator;


    public AddUserResponse execute(AddUserRequest request) {
        List<CoreError> errors = validator.validate(request);
        if (!errors.isEmpty()) {
            return new AddUserResponse(errors);
        } else {
            User user = new User(request.getUsername(), request.getPassword());
            database.add(user);
            return new AddUserResponse(user);
        }
    }
}