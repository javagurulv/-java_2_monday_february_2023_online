package Shop.core.services.fake;

import Shop.core.services.fake.fake_item_generator.RandomItemGeneratorImpl;
import Shop.core.database.Database;
import Shop.core.domain.cart.Cart;
import Shop.core.domain.item.Item;
import Shop.core.domain.user.User;

import java.util.List;

public class FakeDatabaseInitializer {

    private final Database database;

    public FakeDatabaseInitializer(Database database) {
        this.database = database;
    }

    public void initialize() {
        createFakeUsers();
        createFakeCartsForUsers();
        createFakeItems();
    }

    private void createFakeUsers() {
        List<User> fakeUsers = new FakeUserGenerator().createUsers();
        for (User user : fakeUsers) {
            database.accessUserDatabase().save(user);
        }
    }

    private void createFakeCartsForUsers() {
        List<Cart> fakeCarts = new FakeCartGenerator().createCartsForUsers(database.accessUserDatabase().getAllUsers());
        for (Cart cart : fakeCarts) {
            database.accessCartDatabase().save(cart);
        }
    }

    private void createFakeItems() {
        List<Item> fakeItems = new RandomItemGeneratorImpl().createItems();
        for (Item item : fakeItems) {
            database.accessItemDatabase().save(item);
        }
    }

}
