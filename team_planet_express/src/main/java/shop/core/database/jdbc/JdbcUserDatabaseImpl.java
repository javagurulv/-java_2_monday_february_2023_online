package shop.core.database.jdbc;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Component;
import shop.core.database.UserDatabase;
import shop.core.database.jdbc.row_mapper.UserRowMapper;
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

    //TODO what's with Optional?
    @Override
    public Optional<User> findById(Long itemId) {
        String sql = "SELECT * FROM user WHERE id = ?;";
        Object[] args = new Object[]{itemId};
        return jdbcTemplate.query(sql, new UserRowMapper(), args).stream().findFirst();
    }

    //TODO make login as unique?
    @Override
    public Optional<User> findByLoginName(String login) {
        String sql = "SELECT * FROM user WHERE login = ?;";
        Object[] args = new Object[]{login};
        return jdbcTemplate.query(sql, new UserRowMapper(), args).stream().findFirst();
    }

    @Override
    public List<User> getAllUsers() {
        String sql = "SELECT * FROM user;";
        return jdbcTemplate.query(sql, new UserRowMapper());
    }

}
