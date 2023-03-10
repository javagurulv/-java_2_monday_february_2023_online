package users.services;

import users.database.Database;

public class ValidateUserService {

    private Database database;

    public ValidateUserService(Database database) {
        this.database = database;
    }

    public boolean execute(Long id, String password) {
        return database.login(id, password);
    }
}