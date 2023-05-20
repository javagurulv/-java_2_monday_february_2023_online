package shop.core.database;

public interface Database {

    UserDatabase accessUserDatabase();

    ItemDatabase accessItemDatabase();

    CartDatabase accessCartDatabase();

    CartItemDatabase accessCartItemDatabase();

}
