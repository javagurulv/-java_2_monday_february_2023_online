package shop.core.database.in_memory;

import shop.core.database.*;

//@Component
public class InMemoryRepository implements Repository {

    private final UserRepository userRepository;
    private final ItemRepository itemRepository;
    private final CartRepository cartRepository;
    private final CartItemRepository cartItemRepository;

    public InMemoryRepository() {
        this.userRepository = new InMemoryUserRepositoryImpl();
        this.itemRepository = new InMemoryItemRepositoryImpl();
        this.cartRepository = new InMemoryCartRepositoryImpl();
        this.cartItemRepository = new InMemoryCartItemRepositoryImpl();
    }

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
