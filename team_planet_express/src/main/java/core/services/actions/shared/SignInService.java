package core.services.actions.shared;

import core.database.Database;
import core.domain.user.User;
import core.services.exception.InvalidLoginNameException;
import core.services.exception.InvalidLoginPasswordException;
import core.support.MutableLong;

import java.util.Optional;

public class SignInService {

    private static final String ERROR_WRONG_PASSWORD = "Error: wrong password.";
    private static final String ERROR_WRONG_NAME = "Error: wrong name.";

    private final Database database;
    private final MutableLong currentUserId;

    public SignInService(Database database, MutableLong currentUserId) {
        this.database = database;
        this.currentUserId = currentUserId;
    }

    public void execute(String name, String password) {
        Optional<User> currentUser = database.accessUserDatabase().findByLogin(name);
        if (currentUser.isEmpty()) {
            throw new InvalidLoginNameException(ERROR_WRONG_NAME);
        }
        if (!currentUser.get().getPassword().equals(password)) {
            throw new InvalidLoginPasswordException(ERROR_WRONG_PASSWORD);
        }
        currentUserId.setValue(currentUser.get().getId());
    }

}
