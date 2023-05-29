package shop.acceptance_tests.custom;

import org.springframework.beans.factory.annotation.Autowired;
import shop.acceptance_tests.ApplicationContextSetup;
import shop.acceptance_tests.custom.tester.*;
import shop.core.domain.user.User;
import shop.core.domain.user.UserRole;
import shop.core.services.user.UserCreationData;
import shop.core.services.user.UserService;
import shop.core.support.CurrentUserId;

public abstract class CustomAcceptanceTest {

    protected final ApplicationContextSetup applicationContextSetup = new ApplicationContextSetup();
    protected @Autowired ListShopItemsTester listShopItemsTester;
    protected @Autowired AddItemToCartTester addItemToCartTester;
    protected @Autowired RemoveItemFromCartTester removeItemFromCartTester;
    protected @Autowired ListCartItemsTester listCartItemsTester;
    protected @Autowired BuyTester buyTester;

    private @Autowired UserService userService;
    private @Autowired CurrentUserId currentUserId;

    protected void setupDefaultUser() {
        UserCreationData userCreationData = new UserCreationData(UserRole.GUEST.getDefaultName(), "", "", UserRole.GUEST);
        User currentUser = userService.findGuestWithOpenCart().orElseGet(
                () -> userService.createUser(userCreationData));
        currentUserId.setValue(currentUser.getId());
    }

}
