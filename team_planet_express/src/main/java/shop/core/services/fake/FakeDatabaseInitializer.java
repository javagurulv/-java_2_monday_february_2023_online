package shop.core.services.fake;

import org.springframework.transaction.annotation.Transactional;
import shop.core.database.CartRepository;
import shop.core.database.ItemRepository;
import shop.core.database.UserRepository;
import shop.core.domain.cart.Cart;
import shop.core.domain.item.Item;
import shop.core.domain.user.User;
import shop.core.services.fake.fake_item_generator.RandomItemGeneratorImpl;

import java.util.List;

@Transactional
public class FakeDatabaseInitializer {

    private final UserRepository userRepository;
    private final ItemRepository itemRepository;
    private final CartRepository cartRepository;

    public FakeDatabaseInitializer(UserRepository userRepository,
                                   ItemRepository itemRepository,
                                   CartRepository cartRepository) {
        this.userRepository = userRepository;
        this.itemRepository = itemRepository;
        this.cartRepository = cartRepository;
    }

    public void initialize() {
        createFakeUsers();
        createFakeCartsForUsers();
        createFakeItems();
    }

    private void createFakeUsers() {
        List<User> fakeUsers = new FakeUserGenerator().createUsers();
        for (User user : fakeUsers) {
            userRepository.save(user);
        }
    }

    private void createFakeCartsForUsers() {
        List<Cart> fakeCarts = new FakeCartGenerator().createCartsForUsers(userRepository.getAllUsers());
        for (Cart cart : fakeCarts) {
            cartRepository.save(cart);
        }
    }

    private void createFakeItems() {
        List<Item> fakeItems = new RandomItemGeneratorImpl().createItems();
        for (Item item : fakeItems) {
            itemRepository.save(item);
        }
    }

}
