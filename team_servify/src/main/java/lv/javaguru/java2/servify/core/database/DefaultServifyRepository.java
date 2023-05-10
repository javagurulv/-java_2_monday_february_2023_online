package lv.javaguru.java2.servify.core.database;

import lv.javaguru.java2.servify.domain.UserEntity;
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
    public void add(UserEntity user) {

    }

    @Override
    public boolean deactivateUser(Long userId) {
        return false;
    }

    @Override
    public List<UserEntity> getAllUsers() {
        return null;
    }
}
