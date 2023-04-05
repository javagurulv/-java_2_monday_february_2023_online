package Shop;

import Shop.console_ui.UIMenu;
import Shop.core.database.Database;
import Shop.core.domain.user.User;
import Shop.core.domain.user.UserRole;
import Shop.core.services.fake.FakeDatabaseInitializer;
import Shop.core.services.user.UserRecord;
import Shop.core.services.user.UserService;
import Shop.core.support.MutableLong;

public class ShopApplication {

    public static final String BLANK = "";

    public static void main(String[] args) {
        ApplicationContext context = initApplicationContext();

        UIMenu uiMenu = context.getBean(UIMenu.class);

        uiMenu.startUI();

    }

    private static ApplicationContext initApplicationContext() {
        ApplicationContext context = new ApplicationContext();
        new FakeDatabaseInitializer(context.getBean(Database.class)).initialize();
        UserService userService = context.getBean(UserService.class);
        UserRecord userRecord = new UserRecord(UserRole.GUEST.getDefaultName(), BLANK, BLANK, UserRole.GUEST);
        User currentUser = userService.findGuestWithOpenCart().orElseGet(
                () -> userService.createUser(userRecord));
        MutableLong currentUserId = context.getBean(MutableLong.class);
        currentUserId.setValue(currentUser.getId());
        return context;
    }

}
