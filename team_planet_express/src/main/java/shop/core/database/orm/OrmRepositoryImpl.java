package shop.core.database.orm;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import shop.core.database.*;

@Component
public class OrmRepositoryImpl implements Repository {

    @Autowired
    private UserRepository userRepository;
    @Autowired
    private ItemRepository itemRepository;
    @Autowired
    private CartRepository cartRepository;
    @Autowired
    private CartItemRepository cartItemRepository;

    @Override
    public UserRepository accessUserDatabase() {
        return userRepository;
    }

    @Override
    public ItemRepository accessItemDatabase() {
        return itemRepository;
    }

    @Override
    public CartRepository accessCartDatabase() {
        return cartRepository;
    }

    @Override
    public CartItemRepository accessCartItemDatabase() {
        return cartItemRepository;
    }

}
