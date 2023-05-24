package lv.fitness_app.database;

import lv.fitness_app.core.domain.User;

import java.util.List;

public interface Database {

    void add(User user);

    void deleteUser(String email);

    List<User> getAllUsers();

    User findUserByEmail(String email);
}
