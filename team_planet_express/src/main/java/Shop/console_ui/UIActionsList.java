package Shop.console_ui;

import Shop.ApplicationContext;
import Shop.console_ui.actions.UIAction;
import Shop.console_ui.actions.admin.ChangeUserDataUIAction;
import Shop.console_ui.actions.customer.*;
import Shop.console_ui.actions.guest.SignUpUIAction;
import Shop.console_ui.actions.manager.AddItemToShopUIAction;
import Shop.console_ui.actions.manager.ChangeItemDataUIAction;
import Shop.console_ui.actions.shared.ExitUIAction;
import Shop.console_ui.actions.shared.SearchItemUIAction;
import Shop.console_ui.actions.shared.SignInUIAction;
import Shop.console_ui.actions.shared.SignOutUIAction;
import Shop.core.database.Database;
import Shop.core.domain.user.User;
import Shop.core.domain.user.UserRole;
import Shop.core.services.exception.ServiceMissingDataException;
import Shop.core.support.MutableLong;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

public class UIActionsList {

    private final Database database;
    private final MutableLong currentUserId;
    private final List<UIAction> uiActionsList;

    public UIActionsList(ApplicationContext context) {
        this.database = context.getBean(Database.class);
        this.currentUserId = context.getBean(MutableLong.class);
        this.uiActionsList = createUIActionsList(context);
    }

    public List<UIAction> getUIActionsListForUserRole() {
        Optional<User> currentUser = database.accessUserDatabase().findById(currentUserId.getValue());
        UserRole filterRole = currentUser.isEmpty() ? UserRole.GUEST : currentUser.get().getUserRole();
        return uiActionsList.stream()
                .filter(uiAction -> filterRole.checkPermission(uiAction.getAccessNumber()))
                .collect(Collectors.toList());
    }

    public String getCurrentUserName() {
        return getUserById(currentUserId.getValue()).getName();
    }

    private List<UIAction> createUIActionsList(ApplicationContext context) {
        List<UIAction> uiActions = new ArrayList<>();
        uiActions.add(context.getBean(ListShopItemsUIAction.class));
        uiActions.add(context.getBean(SearchItemUIAction.class));
        uiActions.add(context.getBean(AddItemToCartUIAction.class));
        uiActions.add(context.getBean(RemoveItemFromCartUIAction.class));
        uiActions.add(context.getBean(ListCartItemsUIAction.class));
        uiActions.add(context.getBean(BuyUIAction.class));
        uiActions.add(context.getBean(AddItemToShopUIAction.class));
        uiActions.add(context.getBean(ChangeItemDataUIAction.class));
        uiActions.add(context.getBean(ChangeUserDataUIAction.class));
        uiActions.add(context.getBean(SignInUIAction.class));
        uiActions.add(context.getBean(SignUpUIAction.class));
        uiActions.add(context.getBean(SignOutUIAction.class));
        uiActions.add(context.getBean(ExitUIAction.class));

        return uiActions;
    }

    //TODO yeet, duplicate
    private User getUserById(Long userId) {
        return database.accessUserDatabase().findById(userId)
                .orElseThrow(ServiceMissingDataException::new);
    }

}
