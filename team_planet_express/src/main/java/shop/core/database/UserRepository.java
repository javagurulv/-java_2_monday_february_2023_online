package shop.core.database;


import shop.core.domain.user.User;

import java.util.List;
import java.util.Optional;

public interface UserRepository {

    User save(User user);

    Optional<User> findById(Long userId);

    Optional<User> findByLoginName(String login);

    List<User> getAllUsers();

}
