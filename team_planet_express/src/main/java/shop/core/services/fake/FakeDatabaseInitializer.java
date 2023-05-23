package shop.core.services.fake;

import org.springframework.transaction.annotation.Transactional;
import shop.core.database.Repository;
import shop.core.domain.cart.Cart;
import shop.core.domain.item.Item;
import shop.core.domain.user.User;
import shop.core.services.fake.fake_item_generator.RandomItemGeneratorImpl;

import java.util.List;

@Transactional
public class FakeDatabaseInitializer {

    private final Repository repository;

    public FakeDatabaseInitializer(Repository repository) {
        this.repository = repository;
    }

    public void initialize() {
        createFakeUsers();
        createFakeCartsForUsers();
        createFakeItems();
    }

    private void createFakeUsers() {
        List<User> fakeUsers = new FakeUserGenerator().createUsers();
        for (User user : fakeUsers) {
            repository.accessUserDatabase().save(user);
        }
    }

    private void createFakeCartsForUsers() {
        List<Cart> fakeCarts = new FakeCartGenerator().createCartsForUsers(repository.accessUserDatabase().getAllUsers());
        for (Cart cart : fakeCarts) {
            repository.accessCartDatabase().save(cart);
        }
    }

    private void createFakeItems() {
        List<Item> fakeItems = new RandomItemGeneratorImpl().createItems();
        for (Item item : fakeItems) {
            repository.accessItemDatabase().save(item);
        }
    }

}
