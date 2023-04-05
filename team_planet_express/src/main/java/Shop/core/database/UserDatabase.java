package Shop.core.database;


import Shop.core.domain.user.User;

import java.util.List;
import java.util.Optional;

public interface UserDatabase {

    User save(User user);

    Optional<User> findById(Long itemId);

    Optional<User> findByLogin(String login);

    List<User> getAllUsers();

}