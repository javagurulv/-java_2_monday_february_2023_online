package lv.fitness_app.database;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

@Component
public
class ConnectionManager {

    @Value("${connectionManager.url}")
    private String url;
    @Value("${connectionManager.username}")
    private String username;
    @Value("${connectionManager.password}")
    private String password;


    public Connection connect() throws SQLException {
//        String url = "jdbc:postgresql://localhost:5432/fitness_app";
//        String username = "postgres";
//        String password = "Mersedes1";
        return DriverManager.getConnection(url, username, password);
    }
}
