package Shop;

import Shop.console_ui.UIActionsList;
import Shop.console_ui.UIMenu;
import Shop.console_ui.UserCommunication;
import Shop.console_ui.actions.admin.ChangeUserDataUIAction;
import Shop.console_ui.actions.customer.*;
import Shop.console_ui.actions.guest.SignUpUIAction;
import Shop.console_ui.actions.manager.AddItemToShopUIAction;
import Shop.console_ui.actions.manager.ChangeItemDataUIAction;
import Shop.console_ui.actions.shared.ExitUIAction;
import Shop.console_ui.actions.shared.SearchItemUIAction;
import Shop.console_ui.actions.shared.SignInUIAction;
import Shop.console_ui.actions.shared.SignOutUIAction;
import Shop.console_ui.item_list.ordering.OrderingUIElement;
import Shop.console_ui.item_list.paging.PagingUIElement;
import Shop.core.database.Database;
import Shop.core.services.actions.admin.ChangeUserDataService;
import Shop.core.services.actions.customer.*;
import Shop.core.services.actions.guest.SignUpService;
import Shop.core.services.actions.manager.AddItemToShopService;
import Shop.core.services.actions.manager.ChangeItemDataService;
import Shop.core.services.actions.shared.ExitService;
import Shop.core.services.actions.shared.SearchItemService;
import Shop.core.services.actions.shared.SignInService;
import Shop.core.services.actions.shared.SignOutService;
import Shop.core.services.item.ordering.OrderingService;
import Shop.core.services.item.paging.PagingService;
import Shop.core.services.user.UserService;
import Shop.core.services.validators.actions.customer.AddItemToCartValidator;
import Shop.core.services.validators.actions.customer.BuyValidator;
import Shop.core.services.validators.actions.customer.ListCartItemValidator;
import Shop.core.services.validators.actions.customer.RemoveItemFromCartValidator;
import Shop.core.services.validators.actions.guest.SignUpValidator;
import Shop.core.services.validators.actions.manager.AddItemToShopValidator;
import Shop.core.services.validators.actions.manager.ChangeItemDataValidator;
import Shop.core.services.validators.actions.shared.SearchItemValidator;
import Shop.core.services.validators.actions.shared.SignInValidator;
import Shop.core.services.validators.actions.shared.SignOutValidator;
import Shop.core.services.validators.cart.CartValidator;
import Shop.core.services.validators.universal.system.MutableLongUserIdValidator;
import Shop.core.services.validators.universal.user_input.InputStringValidator;
import Shop.core.support.MutableLong;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class ApplicationContext {

    private final Map<Class, Object> beans = new HashMap<>();

    public ApplicationContext() {
        beans.put(Database.class, new Database());
        beans.put(MutableLong.class, new MutableLong(0L));
        beans.put(UserCommunication.class, new UserCommunication());

        initValidators();
        initServices();
        initActions();

        beans.put(UIActionsList.class, new UIActionsList(this));
        beans.put(UIMenu.class, new UIMenu(getBean(UIActionsList.class), getBean(UserCommunication.class)));

    }

    private void initValidators() {
        beans.put(CartValidator.class, new CartValidator(getBean(Database.class)));
        beans.put(MutableLongUserIdValidator.class, new MutableLongUserIdValidator());
        beans.put(InputStringValidator.class, new InputStringValidator());
        beans.put(SearchItemValidator.class, new SearchItemValidator(getBean(InputStringValidator.class)));
        beans.put(AddItemToCartValidator.class, new AddItemToCartValidator(
                getBean(Database.class), getBean(MutableLongUserIdValidator.class),
                getBean(CartValidator.class), getBean(InputStringValidator.class)
        ));
        beans.put(RemoveItemFromCartValidator.class, new RemoveItemFromCartValidator(
                getBean(Database.class), getBean(MutableLongUserIdValidator.class),
                getBean(CartValidator.class), getBean(InputStringValidator.class)
        ));
        beans.put(ListCartItemValidator.class, new ListCartItemValidator(
                getBean(MutableLongUserIdValidator.class),
                getBean(CartValidator.class)
        ));
        beans.put(BuyValidator.class, new BuyValidator(
                getBean(Database.class), getBean(MutableLongUserIdValidator.class),
                getBean(CartValidator.class)
        ));
        beans.put(AddItemToShopValidator.class, new AddItemToShopValidator(
                getBean(Database.class), getBean(InputStringValidator.class)
        ));
        beans.put(ChangeItemDataValidator.class, new ChangeItemDataValidator(
                getBean(Database.class), getBean(InputStringValidator.class)
        ));
        beans.put(SignInValidator.class, new SignInValidator(
                getBean(Database.class), getBean(MutableLongUserIdValidator.class),
                getBean(InputStringValidator.class)
        ));
        beans.put(SignUpValidator.class, new SignUpValidator(
                getBean(Database.class), getBean(MutableLongUserIdValidator.class),
                getBean(InputStringValidator.class)
        ));
        beans.put(SignOutValidator.class, new SignOutValidator(
                getBean(MutableLongUserIdValidator.class)
        ));
    }

    private void initServices() {
        beans.put(ListShopItemsService.class, new ListShopItemsService(getBean(Database.class)));
        beans.put(ChangeUserDataService.class, new ChangeUserDataService(getBean(Database.class)));
        beans.put(OrderingService.class, new OrderingService());
        beans.put(PagingService.class, new PagingService());
        beans.put(UserService.class, new UserService(getBean(Database.class)));
        beans.put(SearchItemService.class, new SearchItemService(
                getBean(Database.class), getBean(SearchItemValidator.class),
                getBean(OrderingService.class), getBean(PagingService.class)
        ));
        beans.put(AddItemToCartService.class, new AddItemToCartService(
                getBean(Database.class), getBean(AddItemToCartValidator.class)
        ));
        beans.put(RemoveItemFromCartService.class, new RemoveItemFromCartService(
                getBean(Database.class), getBean(RemoveItemFromCartValidator.class)
        ));
        beans.put(ListCartItemsService.class, new ListCartItemsService(
                getBean(Database.class), getBean(ListCartItemValidator.class)
        ));
        beans.put(BuyService.class, new BuyService(
                getBean(Database.class), getBean(BuyValidator.class)
        ));
        beans.put(AddItemToShopService.class, new AddItemToShopService(
                getBean(Database.class), getBean(AddItemToShopValidator.class)
        ));
        beans.put(ChangeItemDataService.class, new ChangeItemDataService(
                getBean(Database.class), getBean(ChangeItemDataValidator.class)
        ));
        beans.put(SignInService.class, new SignInService(
                getBean(Database.class), getBean(SignInValidator.class)
        ));
        beans.put(SignUpService.class, new SignUpService(
                getBean(SignUpValidator.class), getBean(UserService.class)
        ));
        beans.put(SignOutService.class, new SignOutService(
                getBean(SignOutValidator.class), getBean(UserService.class)
        ));
        beans.put(OrderingUIElement.class, new OrderingUIElement(
                getBean(UserCommunication.class)
        ));
        beans.put(PagingUIElement.class, new PagingUIElement(
                getBean(UserCommunication.class)
        ));
        beans.put(ExitService.class, new ExitService());
    }

    private void initActions() {
        beans.put(ListShopItemsUIAction.class, new ListShopItemsUIAction(
                getBean(ListShopItemsService.class),
                getBean(UserCommunication.class)
        ));
        beans.put(SearchItemUIAction.class, new SearchItemUIAction(
                getBean(SearchItemService.class),
                getBean(OrderingUIElement.class),
                getBean(PagingUIElement.class),
                getBean(UserCommunication.class)
        ));
        beans.put(AddItemToCartUIAction.class, new AddItemToCartUIAction(
                getBean(AddItemToCartService.class),
                getBean(MutableLong.class),
                getBean(UserCommunication.class)
        ));
        beans.put(RemoveItemFromCartUIAction.class, new RemoveItemFromCartUIAction(
                getBean(RemoveItemFromCartService.class),
                getBean(MutableLong.class),
                getBean(UserCommunication.class)
        ));
        beans.put(ListCartItemsUIAction.class, new ListCartItemsUIAction(
                getBean(ListCartItemsService.class),
                getBean(MutableLong.class),
                getBean(UserCommunication.class)
        ));
        beans.put(BuyUIAction.class, new BuyUIAction(
                getBean(BuyService.class),
                getBean(MutableLong.class),
                getBean(UserCommunication.class)
        ));
        beans.put(AddItemToShopUIAction.class, new AddItemToShopUIAction(
                getBean(AddItemToShopService.class),
                getBean(UserCommunication.class)
        ));
        beans.put(ChangeItemDataUIAction.class, new ChangeItemDataUIAction(
                getBean(ChangeItemDataService.class),
                getBean(UserCommunication.class)
        ));
        beans.put(ChangeUserDataUIAction.class, new ChangeUserDataUIAction(
                getBean(ChangeUserDataService.class),
                getBean(UserCommunication.class)
        ));
        beans.put(SignInUIAction.class, new SignInUIAction(
                getBean(SignInService.class),
                getBean(MutableLong.class),
                getBean(UserCommunication.class)
        ));
        beans.put(SignUpUIAction.class, new SignUpUIAction(
                getBean(SignUpService.class),
                getBean(MutableLong.class),
                getBean(UserCommunication.class)
        ));
        beans.put(SignOutUIAction.class, new SignOutUIAction(
                getBean(SignOutService.class),
                getBean(MutableLong.class),
                getBean(UserCommunication.class)
        ));
        beans.put(ExitUIAction.class, new ExitUIAction(
                getBean(ExitService.class),
                getBean(UserCommunication.class)
        ));

        beans.put(List.class, new ExitUIAction(
                getBean(ExitService.class),
                getBean(UserCommunication.class)
        ));
    }


    public <T extends Object> T getBean(Class c) {
        return (T) beans.get(c);
    }
}
