package shop.acceptance_tests;

import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import shop.config.ShopConfiguration;
import shop.core.database.Repository;
import shop.core.domain.item.Item;
import shop.core.domain.user.User;
import shop.core.domain.user.UserRole;
import shop.core.services.fake.FakeUserGenerator;
import shop.core.services.fake.fake_item_generator.HardcodedItemGeneratorImpl;
import shop.core.services.user.UserCreationData;
import shop.core.services.user.UserService;
import shop.core.support.CurrentUserId;

import java.util.List;

public class ApplicationContextSetup {

    private static final String BLANK = "";

    public ApplicationContext setupApplicationContext() {
        ApplicationContext applicationContext = new AnnotationConfigApplicationContext(ShopConfiguration.class);
        createFakeItems(applicationContext);
        createFakeUsers(applicationContext);
        setupDefaultUser(applicationContext);
        return applicationContext;
    }

    private void createFakeItems(ApplicationContext applicationContext) {
        List<Item> fakeItems = new HardcodedItemGeneratorImpl().createItems();
        Repository repository = applicationContext.getBean(Repository.class);
        for (Item item : fakeItems) {
            repository.accessItemRepository().save(item);
        }
    }

    private void createFakeUsers(ApplicationContext applicationContext) {
        List<User> fakeUsers = new FakeUserGenerator().createUsers();
        Repository repository = applicationContext.getBean(Repository.class);
        for (User user : fakeUsers) {
            repository.accessUserRepository().save(user);
        }
    }

    private void setupDefaultUser(ApplicationContext applicationContext) {
        UserService userService = applicationContext.getBean(UserService.class);
        UserCreationData userCreationData = new UserCreationData(UserRole.GUEST.getDefaultName(), BLANK, BLANK, UserRole.GUEST);
        User currentUser = userService.findGuestWithOpenCart().orElseGet(
                () -> userService.createUser(userCreationData));
        CurrentUserId currentUserId = applicationContext.getBean(CurrentUserId.class);
        currentUserId.setValue(currentUser.getId());
    }

}
