package storage;

import domain.UserEntity;

import java.util.List;

public interface UserRepository {

    void saveUser(UserEntity user);
    void deleteUser(UserEntity user);
    List<UserEntity> getAllUsers();
}
