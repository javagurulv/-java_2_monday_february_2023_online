package shop.core.database.cleaner;

import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;
import shop.core.database.jpa.JpaCartItemRepository;
import shop.core.database.jpa.JpaCartRepository;
import shop.core.database.jpa.JpaItemRepository;
import shop.core.database.jpa.JpaUserRepository;
import shop.core.domain.Cart;
import shop.core.domain.CartItem;
import shop.core.domain.Item;
import shop.core.domain.User;

import java.util.List;

//TODO YEET
@Component
@Transactional
public class OrmDatabaseCleanerImpl implements DatabaseCleaner {

    @PersistenceContext
    private EntityManager entityManager;

    @Autowired
    private JpaUserRepository userRepository;
    @Autowired
    private JpaItemRepository itemRepository;
    @Autowired
    private JpaCartRepository cartRepository;
    @Autowired
    private JpaCartItemRepository cartItemRepository;

    @Override
    public void clean() {
        List<CartItem> cartItemTableData = cartItemRepository.findAll();
        clearTable(cartItemTableData);
        List<Cart> cartTableData = cartRepository.findAll();
        clearTable(cartTableData);
        List<Item> itemTableData = itemRepository.findAll();
        clearTable(itemTableData);
        List<User> userTableData = userRepository.findAll();
        clearTable(userTableData);
    }

    private void clearTable(List<?> tableData) {
        tableData.forEach(record -> entityManager.remove(record));
    }

}
