package shop.core.database.orm.cleaner;

import org.hibernate.SessionFactory;
import org.hibernate.query.Query;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;
import shop.core.database.DatabaseCleaner;
import shop.core.domain.cart_item.CartItem;

import java.util.List;

@Component
@Transactional
public class OrmDatabaseCleanerImpl implements DatabaseCleaner {

    @Autowired
    private SessionFactory sessionFactory;

    @Override
    public void clean() {
        getTableNames().forEach(this::clearTable);
    }

    private List<String> getTableNames() {
        return List.of(
                "CartItem",
                "Cart",
                "Item",
                "User"
        );
    }

    private void clearTable(String tableName) {
        //TODO ???? deprecated
        Query query = sessionFactory.getCurrentSession().createQuery(
                "DELETE " + tableName);
        query.executeUpdate();
    }

}
