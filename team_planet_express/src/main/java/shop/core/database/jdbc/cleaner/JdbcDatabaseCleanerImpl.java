package shop.core.database.jdbc.cleaner;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import shop.core.database.DatabaseCleaner;

import java.util.List;

//@Component
public class JdbcDatabaseCleanerImpl implements DatabaseCleaner {

    @Autowired
    private JdbcTemplate jdbcTemplate;

    @Override
    public void clean() {
        getTableNames().forEach(tableName -> jdbcTemplate.execute("DELETE FROM " + tableName));
    }

    private List<String> getTableNames() {
        return List.of(
                "cart_item",
                "cart",
                "item",
                "shop_user"
        );
    }

}
