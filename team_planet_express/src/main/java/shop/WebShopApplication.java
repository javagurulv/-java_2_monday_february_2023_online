package shop;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.orm.jpa.HibernateJpaAutoConfiguration;
import org.springframework.context.ConfigurableApplicationContext;
import shop.console_ui.UIMenu;
import shop.core.database.CartRepository;
import shop.core.database.ItemRepository;
import shop.core.database.UserRepository;
import shop.core.database.orm.cleaner.DatabaseCleaner;
import shop.core.domain.User;
import shop.core.enums.UserRole;
import shop.core.services.fake.FakeDatabaseInitializer;
import shop.core.services.user.UserCreationData;
import shop.core.services.user.UserService;
import shop.core.support.CurrentUserId;
import shop.web_ui.config.SpringWebConfiguration;

@SpringBootApplication(exclude = HibernateJpaAutoConfiguration.class)
public class WebShopApplication {

    private static final String BLANK = "";

    public static void main(String[] args) {
        ConfigurableApplicationContext context = SpringApplication.run(SpringWebConfiguration.class);

        context.getBean(DatabaseCleaner.class).clean();
        new FakeDatabaseInitializer(context.getBean(UserRepository.class),
                context.getBean(ItemRepository.class),
                context.getBean(CartRepository.class)
        ).initialize();

        UserService userService = context.getBean(UserService.class);
        UserCreationData userCreationData = new UserCreationData(UserRole.GUEST.getDefaultName(), BLANK, BLANK, UserRole.GUEST);
        User currentUser = userService.findGuestWithOpenCart().orElseGet(
                () -> userService.createUser(userCreationData));
        CurrentUserId currentUserId = context.getBean(CurrentUserId.class);
        currentUserId.setValue(currentUser.getId());

        UIMenu uiMenu = context.getBean(UIMenu.class);
        uiMenu.startUI();

    }

}
