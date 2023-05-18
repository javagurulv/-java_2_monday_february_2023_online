package lv.fitness_app.users.core.services;

import lv.fitness_app.database.Database;
import lv.fitness_app.users.core.domain.User;
import lv.fitness_app.users.core.requests.LoginUserRequest;
import lv.fitness_app.users.core.responses.CoreError;
import lv.fitness_app.users.core.responses.LoginUserResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class LoginUserService {

    @Autowired
    private Database database;
    @Autowired private LoginUserRequestValidator validator;


    public LoginUserResponse execute(LoginUserRequest request) {
        List<CoreError> errors = validator.validate(request);
        boolean isUserLogged = false;
        if (!errors.isEmpty()) {
            return new LoginUserResponse(errors);
        } else if (database.findUserByEmail(request.getEmail()) != null ) {
            User user = database.findUserByEmail(request.getEmail());
            if (user.getEmail().equals(request.getEmail()) && user.getPassword().equals(request.getPassword())) {
                isUserLogged = true;
            }
        }
        return new LoginUserResponse(isUserLogged);
    }

}