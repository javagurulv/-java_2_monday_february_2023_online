package java2.fitness_app.users.users.core.services;

import java2.fitness_app.users.users.core.database.Database;

public class RemoveUserService {

    private Database database;

    public RemoveUserService(Database database) {
        this.database = database;
    }

    public boolean execute(Long id, String password) {
        return database.deleteUser(id, password);
    }

}
