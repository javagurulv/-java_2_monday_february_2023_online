package shop;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ConfigurableApplicationContext;
import shop.core.database.cleaner.DatabaseCleaner;
import shop.core.database.jpa.JpaCartRepository;
import shop.core.database.jpa.JpaItemRepository;
import shop.core.database.jpa.JpaUserRepository;
import shop.core.domain.User;
import shop.core.enums.UserRole;
import shop.core.services.fake.FakeDatabaseInitializer;
import shop.core.services.user.UserCreationData;
import shop.core.services.user.UserService;
import shop.core.support.CurrentUserId;
import shop.web_ui.config.SpringWebConfiguration;

@SpringBootApplication
public class WebShopApplication {

    private static final String BLANK = "";

    public static void main(String[] args) {
        ConfigurableApplicationContext context = SpringApplication.run(SpringWebConfiguration.class);

        context.getBean(DatabaseCleaner.class).clean();
        new FakeDatabaseInitializer(context.getBean(JpaUserRepository.class),
                context.getBean(JpaItemRepository.class),
                context.getBean(JpaCartRepository.class)
        ).initialize();

        UserService userService = context.getBean(UserService.class);
        UserCreationData userCreationData = new UserCreationData(UserRole.GUEST.getDefaultName(), BLANK, BLANK, UserRole.GUEST);
        User currentUser = userService.findGuestWithOpenCart().orElseGet(
                () -> userService.createUser(userCreationData));
        CurrentUserId currentUserId = context.getBean(CurrentUserId.class);
        currentUserId.setValue(currentUser.getId());

    }

}
