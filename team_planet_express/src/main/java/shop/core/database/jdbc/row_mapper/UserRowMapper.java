package shop.core.database.jdbc.row_mapper;

import org.springframework.jdbc.core.RowMapper;
import shop.core.domain.user.User;
import shop.core.domain.user.UserRole;

import java.sql.ResultSet;
import java.sql.SQLException;

public class UserRowMapper implements RowMapper<User> {

    @Override
    public User mapRow(ResultSet rs, int rowNum) throws SQLException {
        User user = new User(
                rs.getString("name"),
                rs.getString("login"),
                rs.getString("password"),
                UserRole.valueOf(rs.getString("role"))
        );
        user.setId(rs.getLong("id"));
        return user;
    }

}
