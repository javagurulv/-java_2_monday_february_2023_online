package lv.fitness_app.users.core.services;

import lv.fitness_app.users.core.database.Database;
import lv.fitness_app.users.core.responses.CoreError;
import lv.fitness_app.users.core.responses.RemoveUserResponse;
import lv.fitness_app.users.core.domain.User;
import lv.fitness_app.users.core.requests.RemoveUserRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class RemoveUserService {

    @Autowired private Database database;
    @Autowired private RemoveUserRequestValidator validator;


    public RemoveUserResponse execute(RemoveUserRequest request) {
        List<CoreError> errors = validator.validate(request);
        boolean isUserRemoved = false;
        if (!errors.isEmpty()) {
            return new RemoveUserResponse(errors);
        } else if (database.findUserById(request.getUserId()).isPresent()) {
            User user = database.findUserById(request.getUserId()).get();
            if (user.getId().equals(request.getUserId()) && user.getPassword().equals(request.getPassword())) {
                database.deleteUser(user);
                isUserRemoved = true;
            }
        }
        return new RemoveUserResponse(isUserRemoved);
    }
}