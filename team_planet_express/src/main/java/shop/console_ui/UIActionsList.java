package shop.console_ui;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import shop.console_ui.actions.UIAction;
import shop.console_ui.actions.admin.ChangeUserDataUIAction;
import shop.console_ui.actions.customer.*;
import shop.console_ui.actions.guest.SignUpUIAction;
import shop.console_ui.actions.manager.AddItemToShopUIAction;
import shop.console_ui.actions.manager.ChangeItemDataUIAction;
import shop.console_ui.actions.shared.ExitUIAction;
import shop.console_ui.actions.shared.SearchItemUIAction;
import shop.console_ui.actions.shared.SignInUIAction;
import shop.console_ui.actions.shared.SignOutUIAction;
import shop.core.database.Database;
import shop.core.domain.user.User;
import shop.core.domain.user.UserRole;
import shop.core.support.CurrentUserId;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Component
public class UIActionsList {
    private final List<UIAction> uiActionsList;
    @Autowired
    Database database;
    @Autowired
    CurrentUserId currentUserId;

    @Autowired
    public UIActionsList(List<UIAction> uiActionsList) {
        this.uiActionsList = sortUIActionsList(uiActionsList);
    }

    public List<UIAction> getUIActionsListForUserRole() {
        Optional<User> currentUser = database.accessUserDatabase().findById(currentUserId.getValue());
        UserRole filterRole = currentUser.isEmpty() ? UserRole.GUEST : currentUser.get().getUserRole();
        return uiActionsList.stream()
                .filter(uiAction -> filterRole.checkPermission(uiAction.getAccessNumber()))
                .collect(Collectors.toList());
    }

    private List<UIAction> sortUIActionsList(List<UIAction> uiActionsList) {
        List<UIAction> sortedUIActions = new ArrayList<>();
        sortedUIActions.add(uiActionsList.stream().filter(uiAction -> uiAction instanceof ListShopItemsUIAction).findFirst().get());
        sortedUIActions.add(uiActionsList.stream().filter(uiAction -> uiAction instanceof SearchItemUIAction).findFirst().get());
        sortedUIActions.add(uiActionsList.stream().filter(uiAction -> uiAction instanceof AddItemToCartUIAction).findFirst().get());
        sortedUIActions.add(uiActionsList.stream().filter(uiAction -> uiAction instanceof RemoveItemFromCartUIAction).findFirst().get());
        sortedUIActions.add(uiActionsList.stream().filter(uiAction -> uiAction instanceof ListCartItemsUIAction).findFirst().get());
        sortedUIActions.add(uiActionsList.stream().filter(uiAction -> uiAction instanceof BuyUIAction).findFirst().get());
        sortedUIActions.add(uiActionsList.stream().filter(uiAction -> uiAction instanceof AddItemToShopUIAction).findFirst().get());
        sortedUIActions.add(uiActionsList.stream().filter(uiAction -> uiAction instanceof ChangeItemDataUIAction).findFirst().get());
        sortedUIActions.add(uiActionsList.stream().filter(uiAction -> uiAction instanceof ChangeUserDataUIAction).findFirst().get());
        sortedUIActions.add(uiActionsList.stream().filter(uiAction -> uiAction instanceof SignInUIAction).findFirst().get());
        sortedUIActions.add(uiActionsList.stream().filter(uiAction -> uiAction instanceof SignUpUIAction).findFirst().get());
        sortedUIActions.add(uiActionsList.stream().filter(uiAction -> uiAction instanceof SignOutUIAction).findFirst().get());
        sortedUIActions.add(uiActionsList.stream().filter(uiAction -> uiAction instanceof ExitUIAction).findFirst().get());
        return sortedUIActions;
    }

}
