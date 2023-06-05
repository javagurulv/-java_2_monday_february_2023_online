package lv.fitness_app.database;

import lv.fitness_app.core.domain.User;

import java.util.List;

public interface UserRepository {

    void add(User user);

    void deleteUser(User user);

    List<User> getAllUsers();

    User findUserByEmail(String email);
}
