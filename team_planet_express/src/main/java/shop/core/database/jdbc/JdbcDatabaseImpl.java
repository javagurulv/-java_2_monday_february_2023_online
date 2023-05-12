package shop.core.database.jdbc;

import org.springframework.stereotype.Component;
import shop.core.database.*;

@Component
public class JdbcDatabaseImpl implements Database {

    private final UserDatabase userDatabase;
    private final ItemDatabase itemDatabase;
    private final CartDatabase cartDatabase;
    private final CartItemDatabase cartItemDatabase;

    public JdbcDatabaseImpl() {
        this.userDatabase = new JdbcUserDatabaseImpl();
        this.itemDatabase = new JdbcItemDatabaseImpl();
        this.cartDatabase = new JdbcCartDatabaseImpl();
        this.cartItemDatabase = new JdbcCartItemDatabaseImpl();
    }

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
