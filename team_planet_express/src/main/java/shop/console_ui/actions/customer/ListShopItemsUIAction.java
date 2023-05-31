package shop.console_ui.actions.customer;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import shop.console_ui.UserCommunication;
import shop.console_ui.actions.UIAction;
import shop.console_ui.item_list.ItemStringProvider;
import shop.core.domain.user.UserRole;
import shop.core.requests.customer.ListShopItemsRequest;
import shop.core.responses.customer.ListShopItemsResponse;
import shop.core.services.actions.customer.ListShopItemsService;
import shop.core.support.CurrentUserId;

@Component
public class ListShopItemsUIAction extends UIAction {

    private static final String ACTION_NAME = "List items";
    private static final int ACCESS_NUMBER = UserRole.getAccessNumber(UserRole.GUEST, UserRole.CUSTOMER, UserRole.MANAGER);

    private static final String HEADER_TEXT = "Shop items:";

    @Autowired
    private ListShopItemsService listShopItemsService;
    @Autowired
    private CurrentUserId currentUserId;
    @Autowired
    private ItemStringProvider itemStringProvider;
    @Autowired
    private UserCommunication userCommunication;

    public ListShopItemsUIAction() {
        super(ACTION_NAME, ACCESS_NUMBER);
    }

    @Override
    public void execute() {
        userCommunication.informUser(HEADER_TEXT);
        ListShopItemsRequest request = new ListShopItemsRequest(currentUserId);
        ListShopItemsResponse response = listShopItemsService.execute(request);
    }

}
