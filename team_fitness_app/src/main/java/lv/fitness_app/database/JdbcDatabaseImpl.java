package lv.fitness_app.database;

import lv.fitness_app.core.domain.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Component;

import java.util.List;
@Component
public class JdbcDatabaseImpl implements Database {

    @Autowired
    private JdbcTemplate jdbcTemplate;

    @Override
    public void add(User user) {
        jdbcTemplate.update(
                "INSERT INTO user_db.user (email, user_name, password, subscription, subscription_ends) "
                        + "VALUES (?, ?, ?, ?, ?)",
                user.getEmail(), user.getUsername(), user.getPassword(), user.getSubscription().name(), user.getEndOfSubscriptionDate()
        );
    }

    @Override
    public void deleteUser(String email) {
        String sql = "DELETE FROM user_db.user WHERE email = ?";
        Object[] args = new Object[] {email};
        jdbcTemplate.update(sql, args);
    }

    @Override
    public List<User> getAllUsers() {
        String sql = "SELECT * FROM user_db.user";
        return jdbcTemplate.query(sql, new UserRowMapper());
    }

    @Override
    public User findUserByEmail(String email) {
        String sql = "SELECT * FROM user_db.user WHERE email = '" + email +"';";
        Object[] args = new Object[] {email};
        return jdbcTemplate.query(sql, new UserRowMapper()).stream()
        .findFirst()
        .orElse(null);
    }
}
