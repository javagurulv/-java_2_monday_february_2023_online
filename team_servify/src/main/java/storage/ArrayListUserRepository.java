package storage;

import domain.UserEntity;

import java.util.ArrayList;
import java.util.List;

public class ArrayListUserRepository implements UserRepository {

    private List<UserEntity> users = new ArrayList<>();

    @Override
    public void saveUser(UserEntity user) {
        users.add(user);
    }

    @Override
    public void deleteUser(UserEntity user) {
        users.remove(user);
    }

    @Override
    public List<UserEntity> getAllUsers() {
        return users;
    }
}
