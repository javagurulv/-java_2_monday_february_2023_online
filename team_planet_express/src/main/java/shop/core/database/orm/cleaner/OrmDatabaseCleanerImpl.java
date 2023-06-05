package shop.core.database.orm.cleaner;

import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;
import shop.core.database.DatabaseCleaner;
import shop.core.database.Repository;
import shop.core.domain.cart.Cart;
import shop.core.domain.cart_item.CartItem;
import shop.core.domain.item.Item;
import shop.core.domain.user.User;

import java.util.List;

@Component
@Transactional
public class OrmDatabaseCleanerImpl implements DatabaseCleaner {

    @Autowired
    private SessionFactory sessionFactory;
    @Autowired
    private Repository repository;

    //TODO shame
    @Override
    public void clean() {
        List<CartItem> cartItemTableData = repository.accessCartItemRepository().getAllCartItems();
        clearTable(cartItemTableData);
        List<Cart> cartTableData = repository.accessCartRepository().getAllCarts();
        clearTable(cartTableData);
        List<Item> itemTableData = repository.accessItemRepository().getAllItems();
        clearTable(itemTableData);
        List<User> userTableData = repository.accessUserRepository().getAllUsers();
        clearTable(userTableData);
    }

    private void clearTable(List<?> tableData) {
        tableData.forEach(record -> sessionFactory.getCurrentSession().remove(record));
    }

//    DEPRECATED DELETE
//    @Override
//    public void clean() {
//        getTableNames().forEach(this::clearTable);
//    }
//
//    private List<String> getTableNames() {
//        return List.of(
//                "CartItem",
//                "Cart",
//                "Item",
//                "User"
//        );
//    }
//
//    private void clearTable(String tableName) {
//        // deprecated
//        Query query = sessionFactory.getCurrentSession().createQuery(
//                "DELETE " + tableName);
//        query.executeUpdate();
//    }

}
