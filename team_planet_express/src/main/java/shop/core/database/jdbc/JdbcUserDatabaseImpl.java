package shop.core.database.jdbc;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Component;
import shop.core.database.UserDatabase;
import shop.core.domain.user.User;

import java.util.List;
import java.util.Optional;

@Component
public class JdbcUserDatabaseImpl implements UserDatabase {

    @Autowired
    private JdbcTemplate jdbcTemplate;

    @Override
    public User save(User user) {
        String sql = "INSERT INTO user (name, login, password, role) VALUES (?, ?, ?, ?);";
        Object[] args = new Object[]{user.getName(), user.getLogin(), user.getPassword(), user.getUserRole().toString()};
        jdbcTemplate.update(sql, args);
        //TODO get ID
        return user;
    }

    @Override
    public Optional<User> findById(Long itemId) {
        return null;
//        return users.stream()
//                .filter(user -> user.getId().equals(itemId))
//                .findFirst();
    }

    @Override
    public Optional<User> findByLoginName(String login) {
        return null;
//        return users.stream()
//                .filter(user -> user.getLogin().equals(login))
//                .findFirst();
    }

    @Override
    public List<User> getAllUsers() {
        return null;
//        return users;
    }

}
