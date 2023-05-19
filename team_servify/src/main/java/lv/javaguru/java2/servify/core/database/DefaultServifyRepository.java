package lv.javaguru.java2.servify.core.database;

import lv.javaguru.java2.servify.domain.UserEntity;
import lv.javaguru.java2.servify.domain.UserType;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import java.util.List;
@Repository
public class DefaultServifyRepository implements UsersDatabase {

    private final JdbcTemplate jdbcTemplate;

    public DefaultServifyRepository(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }

    @Override
    public void add(UserEntity newUser) {
        jdbcTemplate.update(
                "INSERT INTO users (first_name, last_name, email, phone_number)"
                + "VALUES (?, ?, ?, ?)",
                newUser.getFirstName(), newUser.getLastName(), newUser.getEmail(),
                newUser.getPhoneNumber()
        );
    }

    @Override
    public boolean deactivateUser(Long userId) {
        return jdbcTemplate.update(
                "UPDATE users SET is_inactive = FALSE WHERE id = ?",
                userId
        ) == 1;
    }

    @Override
    public List<UserEntity> getAllUsers() {
        return jdbcTemplate.query("SELECT * FROM users", new BeanPropertyRowMapper(UserEntity.class));
    }
}
