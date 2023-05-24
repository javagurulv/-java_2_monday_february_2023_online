package shop.core.database.orm.cleaner;

import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Component
@Transactional
public class DatabaseCleaner {

    @Autowired
    private SessionFactory sessionFactory;

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
