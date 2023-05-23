package shop.core.database;

public interface Repository {

    UserRepository accessUserRepository();

    ItemRepository accessItemRepository();

    CartRepository accessCartRepository();

    CartItemRepository accessCartItemRepository();

}
