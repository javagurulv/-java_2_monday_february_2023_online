package shop.core.database;

public interface Repository {

    UserRepository accessUserDatabase();

    ItemRepository accessItemDatabase();

    CartRepository accessCartDatabase();

    CartItemRepository accessCartItemDatabase();

}
