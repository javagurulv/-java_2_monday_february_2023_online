package shop.core.database.orm.cleaner;

import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import shop.core.database.DatabaseCleaner;

import java.util.List;

@Component
public class OrmDatabaseCleanerImpl implements DatabaseCleaner {

    @Autowired
    private SessionFactory sessionFactory;

    @Override
    public void clean() {
        getTableNames().forEach(
                tableName -> sessionFactory.getCurrentSession().remove(tableName));
    }

    private List<String> getTableNames() {
        return List.of(
                "CartItem",
                "Cart",
                "Item",
                "User"
        );
    }

}
