package lv.fitness_app.users.core.services;

import lv.fitness_app.database.Database;
import lv.fitness_app.users.core.responses.CoreError;
import lv.fitness_app.users.core.responses.RemoveUserResponse;
import lv.fitness_app.users.core.domain.User;
import lv.fitness_app.users.core.requests.RemoveUserRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class RemoveUserService {

    @Autowired
    @Qualifier("inMemory")
    private Database database;
    @Autowired private RemoveUserRequestValidator validator;


    public RemoveUserResponse execute(RemoveUserRequest request) {
        List<CoreError> errors = validator.validate(request);
        boolean isUserRemoved = false;
        if (!errors.isEmpty()) {
            return new RemoveUserResponse(errors);
        } else if (database.findUserByEmail(request.getEmail())!= null) {
            User user = database.findUserByEmail(request.getEmail());
            if (user.getEmail().equals(request.getEmail()) && user.getPassword().equals(request.getPassword())) {
                database.deleteUser(user.getEmail());
                isUserRemoved = true;
            }
        }
        return new RemoveUserResponse(isUserRemoved);
    }
}