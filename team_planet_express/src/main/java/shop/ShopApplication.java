package shop;

import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import shop.config.ShopConfiguration;
import shop.console_ui.UIMenu;
import shop.core.database.DatabaseCleaner;
import shop.core.database.Repository;
import shop.core.domain.user.User;
import shop.core.domain.user.UserRole;
import shop.core.services.fake.FakeDatabaseInitializer;
import shop.core.services.user.UserCreationData;
import shop.core.services.user.UserService;
import shop.core.support.CurrentUserId;

public class ShopApplication {

    private static final String BLANK = "";

    public static void main(String[] args) {

        ApplicationContext applicationContext = new AnnotationConfigApplicationContext(ShopConfiguration.class);

        applicationContext.getBean(DatabaseCleaner.class).clean();
        new FakeDatabaseInitializer(applicationContext.getBean(Repository.class)).initialize();

        UserService userService = applicationContext.getBean(UserService.class);
        UserCreationData userCreationData = new UserCreationData(UserRole.GUEST.getDefaultName(), BLANK, BLANK, UserRole.GUEST);
        User currentUser = userService.findGuestWithOpenCart().orElseGet(
                () -> userService.createUser(userCreationData));
        CurrentUserId currentUserId = applicationContext.getBean(CurrentUserId.class);
        currentUserId.setValue(currentUser.getId());

        UIMenu uiMenu = applicationContext.getBean(UIMenu.class);
        uiMenu.startUI();

    }

}
