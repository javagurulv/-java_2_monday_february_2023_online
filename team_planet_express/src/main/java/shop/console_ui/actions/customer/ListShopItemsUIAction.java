package shop.console_ui.actions.customer;

import shop.console_ui.UserCommunication;
import shop.console_ui.actions.UIAction;
import shop.core.domain.user.UserRole;
import shop.core.requests.customer.ListShopItemsRequest;
import shop.core.responses.customer.ListShopItemsResponse;
import shop.core.services.actions.customer.ListShopItemsService;
import shop.dependency_injection.DIComponent;
import shop.dependency_injection.DIDependency;

@DIComponent
public class ListShopItemsUIAction extends UIAction {

    private static final String ACTION_NAME = "List items";
    private static final int ACCESS_NUMBER = UserRole.getAccessNumber(UserRole.GUEST, UserRole.CUSTOMER);

    private static final String HEADER_TEXT = "Shop items:";

    @DIDependency
    private ListShopItemsService listShopItemsService;
    @DIDependency
    private UserCommunication userCommunication;

    public ListShopItemsUIAction() {
        super(ACTION_NAME, ACCESS_NUMBER);
    }

    @Override
    public void execute() {
        userCommunication.informUser(HEADER_TEXT);
        ListShopItemsRequest request = new ListShopItemsRequest();
        ListShopItemsResponse response = listShopItemsService.execute(request);
        response.getShopItems().forEach(item -> userCommunication.informUser(item.toString()));
    }

}
