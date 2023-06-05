package shop.acceptance_tests.custom;

import org.springframework.beans.factory.annotation.Autowired;
import shop.acceptance_tests.custom.tester.*;
import shop.core.domain.user.User;
import shop.core.domain.user.UserRole;
import shop.core.services.user.UserCreationData;
import shop.core.services.user.UserService;
import shop.core.support.CurrentUserId;

public abstract class CustomAcceptanceTest {

    @Autowired
    protected AddItemToCartTester addItemToCartTester;
    @Autowired
    protected ListShopItemsTester listShopItemsTester;
    @Autowired
    protected RemoveItemFromCartTester removeItemFromCartTester;
    @Autowired
    protected ListCartItemsTester listCartItemsTester;
    @Autowired
    protected BuyTester buyCartTester;
    @Autowired
    private UserService userService;
    @Autowired
    private CurrentUserId currentUserId;

    protected void setupDefaultUser() {
        UserCreationData userCreationData = new UserCreationData(UserRole.GUEST.getDefaultName(), "", "", UserRole.GUEST);
        User currentUser = userService.findGuestWithOpenCart().orElseGet(
                () -> userService.createUser(userCreationData));
        currentUserId.setValue(currentUser.getId());
    }

}
