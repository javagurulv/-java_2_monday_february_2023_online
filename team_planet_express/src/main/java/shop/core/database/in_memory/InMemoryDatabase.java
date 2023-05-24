package shop.core.database.in_memory;

import shop.core.database.*;

//@Component
public class InMemoryDatabase implements Database {

    private final UserDatabase userDatabase;
    private final ItemDatabase itemDatabase;
    private final CartDatabase cartDatabase;
    private final CartItemDatabase cartItemDatabase;

    public InMemoryDatabase() {
        this.userDatabase = new InMemoryUserDatabaseImpl();
        this.itemDatabase = new InMemoryItemDatabaseImpl();
        this.cartDatabase = new InMemoryCartDatabaseImpl();
        this.cartItemDatabase = new InMemoryCartItemDatabaseImpl();
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
