package shop.core.database.jdbc;

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
                convertToUserRoleEnum(rs.getString("role"))
        );
        user.setId(rs.getLong("id"));
        return user;
    }

    //TODO TEMPTRASH
    private UserRole convertToUserRoleEnum(String role) {
        UserRole userRole;
        switch (role) {
            case "CUSTOMER" -> userRole = UserRole.CUSTOMER;
            case "MANAGER" -> userRole = UserRole.MANAGER;
            case "ADMIN" -> userRole = UserRole.ADMIN;
            default -> userRole = UserRole.GUEST;
        }
        return userRole;
    }

}
