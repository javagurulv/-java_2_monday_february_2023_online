package console_ui;

import console_ui.actions.UIAction;
import console_ui.actions.admin.ChangeUserDataUIAction;
import console_ui.actions.customer.*;
import console_ui.actions.guest.SignUpUIAction;
import console_ui.actions.manager.AddItemToShopUIAction;
import console_ui.actions.manager.ChangeItemDataUIAction;
import console_ui.actions.shared.ExitUIAction;
import console_ui.actions.shared.SignInUIAction;
import core.database.Database;
import core.domain.user.User;
import core.domain.user.UserRole;
import core.services.actions.admin.ChangeUserDataService;
import core.services.actions.customer.*;
import core.services.actions.guest.SignUpService;
import core.services.actions.manager.AddItemToShopService;
import core.services.actions.manager.ChangeItemDataService;
import core.services.actions.shared.ExitService;
import core.services.actions.shared.SignInService;
import core.services.validators.customer.AddItemToCartValidator;
import core.services.validators.customer.BuyValidator;
import core.services.validators.customer.ListCartItemValidator;
import core.support.MutableLong;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

public class UIActionsList {

    private final Database database;
    private final MutableLong currentUserId;
    private final UserCommunication userCommunication;
    private final List<UIAction> uiActionsList;

    public UIActionsList(Database database, MutableLong currentUserId, UserCommunication userCommunication) {
        this.database = database;
        this.currentUserId = currentUserId;
        this.userCommunication = userCommunication;
        this.uiActionsList = createUIActionsList();
    }

    public List<UIAction> getUIActionsListForUserRole() {
        //TODO this should not be necessary
        Optional<User> currentUser = database.accessUserDatabase().findById(currentUserId.getValue());
        UserRole filterRole = currentUser.isEmpty() ? UserRole.GUEST : currentUser.get().getUserRole();
        return uiActionsList.stream()
                .filter(uiAction -> filterRole.checkPermission(uiAction.getAccessNumber()))
                .collect(Collectors.toList());
    }

    private List<UIAction> createUIActionsList() {
        //--------------------init service----------------------
        ListShopItemsService listShopItemsService = new ListShopItemsService(database);
        AddItemToCartService addItemToCartService = new AddItemToCartService(database, new AddItemToCartValidator(database), currentUserId);
        RemoveItemFromCartService removeItemFromCartService = new RemoveItemFromCartService(database, currentUserId);
        ListCartItemsService listCartItemsService = new ListCartItemsService(database, new ListCartItemValidator(database), currentUserId);
        BuyService buyService = new BuyService(database, new BuyValidator(database), currentUserId);
        AddItemToShopService addItemToShopService = new AddItemToShopService(database);
        ChangeItemDataService changeItemDataService = new ChangeItemDataService(database);
        ChangeUserDataService changeUserDataService = new ChangeUserDataService(database);
        SignInService signInService = new SignInService(database, currentUserId);
        SignUpService signUpService = new SignUpService(currentUserId);
        ExitService exitService = new ExitService();
        // -----------------------------------------------------
        List<UIAction> uiActions = new ArrayList<>();
        uiActions.add(new ListShopItemsUIAction(listShopItemsService, userCommunication));
        uiActions.add(new AddItemToCartUIAction(addItemToCartService, userCommunication));
        uiActions.add(new RemoveItemFromCartUIAction(removeItemFromCartService, userCommunication));
        uiActions.add(new ListCartItemsUIAction(listCartItemsService, userCommunication));
        uiActions.add(new BuyUIAction(buyService, userCommunication));
        uiActions.add(new AddItemToShopUIAction(addItemToShopService, userCommunication));
        uiActions.add(new ChangeItemDataUIAction(changeItemDataService, userCommunication));
        uiActions.add(new ChangeUserDataUIAction(changeUserDataService, userCommunication));
        uiActions.add(new SignInUIAction(signInService, userCommunication));
        uiActions.add(new SignUpUIAction(signUpService, userCommunication));
        uiActions.add(new ExitUIAction(exitService, userCommunication));
        return uiActions;
    }

}
