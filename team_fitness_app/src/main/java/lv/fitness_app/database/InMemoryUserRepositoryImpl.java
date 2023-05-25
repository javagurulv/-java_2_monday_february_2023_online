package lv.fitness_app.database;

import lv.fitness_app.core.domain.User;

import java.util.ArrayList;
import java.util.List;

//@Component("inMemory")
public class InMemoryUserRepositoryImpl implements UserRepository {

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
