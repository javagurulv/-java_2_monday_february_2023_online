package shop.acceptance_tests;

import org.springframework.context.ApplicationContext;
import shop.core.domain.user.User;
import shop.core.domain.user.UserRole;
import shop.core.services.user.UserCreationData;
import shop.core.services.user.UserService;
import shop.core.support.CurrentUserId;

public class ApplicationContextSetup {

    private static final String BLANK = "";
    ApplicationContext applicationContext;

    public ApplicationContext setupApplicationContext(ApplicationContext applicationContext) {
        this.applicationContext = applicationContext;
        setupDefaultUser();
        return applicationContext;
    }

    private void setupDefaultUser() {
        UserService userService = applicationContext.getBean(UserService.class);
        UserCreationData userCreationData = new UserCreationData(UserRole.GUEST.getDefaultName(), BLANK, BLANK, UserRole.GUEST);
        User currentUser = userService.findGuestWithOpenCart().orElseGet(
                () -> userService.createUser(userCreationData));
        CurrentUserId currentUserId = applicationContext.getBean(CurrentUserId.class);
        currentUserId.setValue(currentUser.getId());
    }

}
