package shop.core.database.jdbc.cleaner;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class DatabaseCleaner {

    @Autowired
    private JdbcTemplate jdbcTemplate;

    public void clean() {
        getTableNames().forEach(tableName -> jdbcTemplate.execute("DELETE FROM " + tableName));
    }

    private List<String> getTableNames() {
        return List.of(
                "cart_item",
                "cart",
                "item",
                "user"
        );
    }

}
