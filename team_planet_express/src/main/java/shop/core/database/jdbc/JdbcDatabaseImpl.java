package shop.core.database.jdbc;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import shop.core.database.*;

@Component
public class JdbcDatabaseImpl implements Database {

    @Autowired
    private UserDatabase userDatabase;
    @Autowired
    private ItemDatabase itemDatabase;
    @Autowired
    private CartDatabase cartDatabase;
    @Autowired
    private CartItemDatabase cartItemDatabase;

    @Override
    public UserDatabase accessUserDatabase() {
        return userDatabase;
    }

    @Override
    public ItemDatabase accessItemDatabase() {
        return itemDatabase;
    }

    @Override
    public CartDatabase accessCartDatabase() {
        return cartDatabase;
    }

    @Override
    public CartItemDatabase accessCartItemDatabase() {
        return cartItemDatabase;
    }

}
