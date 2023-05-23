package shop.core.database.jdbc;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import shop.core.database.*;

//@Component
public class JdbcRepositoryImpl implements Repository {

    @Autowired
    private UserRepository userRepository;
    @Autowired
    private ItemRepository itemRepository;
    @Autowired
    private CartRepository cartRepository;
    @Autowired
    private CartItemRepository cartItemRepository;

    @Override
    public UserRepository accessUserRepository() {
        return userRepository;
    }

    @Override
    public ItemRepository accessItemRepository() {
        return itemRepository;
    }

    @Override
    public CartRepository accessCartRepository() {
        return cartRepository;
    }

    @Override
    public CartItemRepository accessCartItemRepository() {
        return cartItemRepository;
    }

}
