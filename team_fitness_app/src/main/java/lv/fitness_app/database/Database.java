package lv.fitness_app.database;

import lv.fitness_app.users.core.domain.User;

import java.util.List;
import java.util.Optional;

public interface Database {

    void add(User user);

    void deleteUser(String email);

    List<User> getAllUsers();

    User findUserByEmail(String email);
}
