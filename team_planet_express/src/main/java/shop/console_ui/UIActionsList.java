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
import shop.core.database.Repository;
import shop.core.domain.user.User;
import shop.core.domain.user.UserRole;
import shop.core.support.CurrentUser;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Component
public class UIActionsList {
    private final List<UIAction> uiActionsList;
    @Autowired
    Repository repository;
    @Autowired
    CurrentUser currentUser;

    @Autowired
    public UIActionsList(List<UIAction> uiActionsList) {
        this.uiActionsList = sortUIActionsList(uiActionsList);
    }

    public List<UIAction> getUIActionsListForUserRole() {
        Optional<User> currentUser = repository.accessUserRepository().findById(this.currentUser.getUser().getId());
        UserRole filterRole = currentUser.isEmpty() ? UserRole.GUEST : currentUser.get().getUserRole();
        return uiActionsList.stream()
                .filter(uiAction -> filterRole.checkPermission(uiAction.getAccessNumber()))
                .collect(Collectors.toList());
    }

    private List<UIAction> sortUIActionsList(List<UIAction> uiActionsList) {
        List<UIAction> sortedUIActions = new ArrayList<>();
        sortedUIActions.add(uiActionsList.stream().filter(uiAction -> uiAction instanceof ListShopItemsUIAction).findFirst().orElseThrow());
        sortedUIActions.add(uiActionsList.stream().filter(uiAction -> uiAction instanceof SearchItemUIAction).findFirst().orElseThrow());
        sortedUIActions.add(uiActionsList.stream().filter(uiAction -> uiAction instanceof AddItemToCartUIAction).findFirst().orElseThrow());
        sortedUIActions.add(uiActionsList.stream().filter(uiAction -> uiAction instanceof RemoveItemFromCartUIAction).findFirst().orElseThrow());
        sortedUIActions.add(uiActionsList.stream().filter(uiAction -> uiAction instanceof ListCartItemsUIAction).findFirst().orElseThrow());
        sortedUIActions.add(uiActionsList.stream().filter(uiAction -> uiAction instanceof BuyUIAction).findFirst().orElseThrow());
        sortedUIActions.add(uiActionsList.stream().filter(uiAction -> uiAction instanceof AddItemToShopUIAction).findFirst().orElseThrow());
        sortedUIActions.add(uiActionsList.stream().filter(uiAction -> uiAction instanceof ChangeItemDataUIAction).findFirst().orElseThrow());
        sortedUIActions.add(uiActionsList.stream().filter(uiAction -> uiAction instanceof ChangeUserDataUIAction).findFirst().orElseThrow());
        sortedUIActions.add(uiActionsList.stream().filter(uiAction -> uiAction instanceof SignInUIAction).findFirst().orElseThrow());
        sortedUIActions.add(uiActionsList.stream().filter(uiAction -> uiAction instanceof SignUpUIAction).findFirst().orElseThrow());
        sortedUIActions.add(uiActionsList.stream().filter(uiAction -> uiAction instanceof SignOutUIAction).findFirst().orElseThrow());
        sortedUIActions.add(uiActionsList.stream().filter(uiAction -> uiAction instanceof ExitUIAction).findFirst().orElseThrow());
        return sortedUIActions;
    }

}
