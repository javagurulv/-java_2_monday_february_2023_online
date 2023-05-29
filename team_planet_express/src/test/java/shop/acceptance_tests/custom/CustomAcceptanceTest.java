package shop.acceptance_tests.custom;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Import;
import org.springframework.stereotype.Component;
import shop.acceptance_tests.ApplicationContextSetup;
import shop.acceptance_tests.custom.tester.*;
import shop.core.domain.user.User;
import shop.core.domain.user.UserRole;
import shop.core.services.user.UserCreationData;
import shop.core.services.user.UserService;
import shop.core.support.CurrentUserId;

@Component
@Import({
        ListCartItemsTester.class, ListShopItemsTester.class,
        AddItemToCartTester.class, RemoveItemFromCartTester.class,
        BuyTester.class
})
public abstract class CustomAcceptanceTest {

    protected final ApplicationContextSetup applicationContextSetup = new ApplicationContextSetup();
    protected @Autowired ListShopItemsTester listShopItems;
    protected @Autowired AddItemToCartTester addItemToCart;
    protected @Autowired RemoveItemFromCartTester removeItemFromCart;
    protected @Autowired ListCartItemsTester listCartItems;
    protected @Autowired BuyTester buyCart;

    private @Autowired UserService userService;
    private @Autowired CurrentUserId currentUserId;

    protected void setupDefaultUser() {
        UserCreationData userCreationData = new UserCreationData(UserRole.GUEST.getDefaultName(), "", "", UserRole.GUEST);
        User currentUser = userService.findGuestWithOpenCart().orElseGet(
                () -> userService.createUser(userCreationData));
        currentUserId.setValue(currentUser.getId());
    }

}
