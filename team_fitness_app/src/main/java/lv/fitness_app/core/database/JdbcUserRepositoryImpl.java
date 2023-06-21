package lv.fitness_app.core.database;

import lv.fitness_app.core.domain.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;

import java.util.List;
//@Component
public class JdbcUserRepositoryImpl implements UserRepository {

//    @Autowired
    private JdbcTemplate jdbcTemplate;

    @Override
    public void add(User user) {
        jdbcTemplate.update(
                "INSERT INTO user_db.user (email, user_name, password, subscription, subscription_ends) "
                        + "VALUES (?, ?, ?, ?, ?)",
                user.getEmail(), user.getUsername(), user.getPassword(), user.getSubscription(), user.getEndOfSubscriptionDate()
        );
    }

    @Override
    public void deleteUser(User user) {
//        String sql = "DELETE FROM user_db.user WHERE email = ?";
//        Object[] args = new Object[] {email};
//        jdbcTemplate.update(sql, args);
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
