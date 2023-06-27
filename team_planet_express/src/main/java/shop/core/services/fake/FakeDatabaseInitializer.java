package shop.core.services.fake;

import org.springframework.transaction.annotation.Transactional;
import shop.core.database.jpa.JpaCartRepository;
import shop.core.database.jpa.JpaItemRepository;
import shop.core.database.jpa.JpaUserRepository;
import shop.core.domain.Cart;
import shop.core.domain.Item;
import shop.core.domain.User;
import shop.core.services.fake.fake_item_generator.RandomItemGeneratorImpl;

import java.util.List;

//TODO YEET
@Transactional
public class FakeDatabaseInitializer {

    private final JpaUserRepository userRepository;
    private final JpaItemRepository itemRepository;
    private final JpaCartRepository cartRepository;

    public FakeDatabaseInitializer(JpaUserRepository userRepository,
                                   JpaItemRepository itemRepository,
                                   JpaCartRepository cartRepository) {
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
        List<Cart> fakeCarts = new FakeCartGenerator().createCartsForUsers(userRepository.findAll());
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
