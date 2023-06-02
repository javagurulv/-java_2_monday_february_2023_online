package shop.core.database.orm.cleaner;

import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;
import shop.core.database.CartItemRepository;
import shop.core.database.CartRepository;
import shop.core.database.ItemRepository;
import shop.core.database.UserRepository;
import shop.core.domain.cart.Cart;
import shop.core.domain.cart_item.CartItem;
import shop.core.domain.item.Item;
import shop.core.domain.user.User;

import java.util.List;

@Component
@Transactional
public class OrmDatabaseCleanerImpl implements DatabaseCleaner {

    @PersistenceContext
    private EntityManager entityManager;

    @Autowired
    private UserRepository userRepository;
    @Autowired
    private ItemRepository itemRepository;
    @Autowired
    private CartRepository cartRepository;
    @Autowired
    private CartItemRepository cartItemRepository;

    @Override
    public void clean() {
        List<CartItem> cartItemTableData = cartItemRepository.getAllCartItems();
        clearTable(cartItemTableData);
        List<Cart> cartTableData = cartRepository.getAllCarts();
        clearTable(cartTableData);
        List<Item> itemTableData = itemRepository.getAllItems();
        clearTable(itemTableData);
        List<User> userTableData = userRepository.getAllUsers();
        clearTable(userTableData);
    }

    private void clearTable(List<?> tableData) {
        tableData.forEach(record -> entityManager.remove(record));
    }

}
