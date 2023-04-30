package lv.fitness_app.users.core.services;

import lv.fitness_app.users.core.database.Database;
import lv.fitness_app.users.core.domain.User;
import lv.fitness_app.users.core.requests.LoginUserRequest;
import lv.fitness_app.users.core.responses.CoreError;
import lv.fitness_app.users.core.responses.LoginUserResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class LoginUserService {

    @Autowired private Database database;
    @Autowired private LoginUserRequestValidator validator;


    public LoginUserResponse execute(LoginUserRequest request) {
        List<CoreError> errors = validator.validate(request);
        boolean isUserLogged = false;
        if (!errors.isEmpty()) {
            return new LoginUserResponse(errors);
        } else if (database.findUserById(request.getUserIdToLogin()).isPresent()) {
            User user = database.findUserById(request.getUserIdToLogin()).get();
            if (user.getId().equals(request.getUserIdToLogin()) && user.getPassword().equals(request.getPassword())) {
                isUserLogged = true;
            }
        }
        return new LoginUserResponse(isUserLogged);
    }

}