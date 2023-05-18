package lv.fitness_app.database;

import lv.fitness_app.users.core.domain.User;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

//@Component("inMemory")
public class InMemoryDatabaseImpl implements Database {

    private static Long nextId = 1L;

    public List<User> users = new ArrayList<>();

    @Override
    public List<User> getAllUsers() {
        return users;
    }

    @Override
    public User findUserByEmail(String email) {
        return users.stream()
                .findFirst()
                .orElse(null);
    }

    @Override
    public void add(User user) {
        users.add(user);
    }

    @Override
    public void deleteUser(String email) {
        users.remove(email);
    }
}
