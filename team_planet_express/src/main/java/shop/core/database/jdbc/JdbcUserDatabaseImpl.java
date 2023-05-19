package shop.core.database.jdbc;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.support.GeneratedKeyHolder;
import org.springframework.jdbc.support.KeyHolder;
import org.springframework.stereotype.Component;
import shop.core.database.UserDatabase;
import shop.core.database.jdbc.row_mapper.UserRowMapper;
import shop.core.domain.user.User;

import java.sql.PreparedStatement;
import java.sql.Statement;
import java.util.List;
import java.util.Optional;

@Component
public class JdbcUserDatabaseImpl implements UserDatabase {

    @Autowired
    private JdbcTemplate jdbcTemplate;

    @Override
    public User save(User user) {
        String sql = "INSERT INTO `user` (name, login, password, role) VALUES (?, ?, ?, ?);";
        KeyHolder keyHolder = new GeneratedKeyHolder();
        jdbcTemplate.update(connection -> {
            PreparedStatement statement = connection.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS);
            statement.setString(1, user.getName());
            statement.setString(2, user.getLogin());
            statement.setString(3, user.getPassword());
            statement.setString(4, user.getUserRole().toString());
            return statement;
        }, keyHolder);
        if (keyHolder.getKey() != null) {
            user.setId(keyHolder.getKey().longValue());
        }
        return user;
    }

    @Override
    public Optional<User> findById(Long itemId) {
        String sql = "SELECT * FROM `user` WHERE id = ?;";
        Object[] args = new Object[]{itemId};
        return jdbcTemplate.query(sql, new UserRowMapper(), args).stream().findFirst();
    }

    @Override
    public Optional<User> findByLoginName(String login) {
        String sql = "SELECT * FROM `user` WHERE login = ?;";
        Object[] args = new Object[]{login};
        return jdbcTemplate.query(sql, new UserRowMapper(), args).stream().findFirst();
    }

    @Override
    public List<User> getAllUsers() {
        String sql = "SELECT * FROM `user`;";
        return jdbcTemplate.query(sql, new UserRowMapper());
    }

}
