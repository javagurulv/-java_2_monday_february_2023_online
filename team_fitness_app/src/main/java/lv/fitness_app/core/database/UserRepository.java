package lv.fitness_app.core.database;

import lv.fitness_app.core.domain.User;

import java.util.List;
import java.util.Optional;

public interface UserRepository {

    void add(User user);

    void deleteUser(User user);

    List<User> getAllUsers();

    User findUserByEmail(String email);
    Optional<User> getByEmail(String email);

    boolean deleteByEmail(String email);
}
