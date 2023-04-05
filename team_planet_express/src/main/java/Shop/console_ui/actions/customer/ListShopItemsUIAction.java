package Shop.console_ui.actions.customer;

import Shop.core.domain.user.UserRole;
import Shop.core.requests.customer.ListShopItemsRequest;
import Shop.core.responses.customer.ListShopItemsResponse;
import Shop.console_ui.UserCommunication;
import Shop.console_ui.actions.UIAction;
import Shop.core.services.actions.customer.ListShopItemsService;

public class ListShopItemsUIAction extends UIAction {

    private static final String ACTION_NAME = "List items";
    private static final int ACCESS_NUMBER = UserRole.getAccessNumber(UserRole.GUEST, UserRole.CUSTOMER);

    private static final String HEADER_TEXT = "Shop items:";

    private final ListShopItemsService listShopItemsService;
    private final UserCommunication userCommunication;

    public ListShopItemsUIAction(ListShopItemsService listShopItemsService, UserCommunication userCommunication) {
        super(ACTION_NAME, ACCESS_NUMBER);
        this.listShopItemsService = listShopItemsService;
        this.userCommunication = userCommunication;
    }

    @Override
    public void execute() {
        userCommunication.informUser(HEADER_TEXT);
        ListShopItemsRequest request = new ListShopItemsRequest();
        ListShopItemsResponse response = listShopItemsService.execute(request);
        response.getShopItems().forEach(item -> userCommunication.informUser(item.toString()));
    }

}
