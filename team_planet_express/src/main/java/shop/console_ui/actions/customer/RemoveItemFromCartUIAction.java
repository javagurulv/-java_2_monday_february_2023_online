package shop.console_ui.actions.customer;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import shop.console_ui.UserCommunication;
import shop.console_ui.actions.UIAction;
import shop.core.domain.user.UserRole;
import shop.core.requests.customer.RemoveItemFromCartRequest;
import shop.core.responses.customer.RemoveItemFromCartResponse;
import shop.core.services.actions.customer.RemoveItemFromCartService;
import shop.core.support.CurrentUserId;

@Component
public class RemoveItemFromCartUIAction extends UIAction {

    private static final String ACTION_NAME = "Remove item from the cart";
    private static final int ACCESS_NUMBER = UserRole.getAccessNumber(UserRole.GUEST, UserRole.CUSTOMER);

    private static final String PROMPT_TOPIC_ITEM = "an item you wish to remove: ";
    private static final String MESSAGE_ITEM_REMOVED = "Item removed from your cart.";

    @Autowired
    private RemoveItemFromCartService removeItemFromCartService;
    @Autowired
    private CurrentUserId currentUserId;
    @Autowired
    private UserCommunication userCommunication;


    public RemoveItemFromCartUIAction() {
        super(ACTION_NAME, ACCESS_NUMBER);
    }

    @Override
    public void execute() {
        String itemName = userCommunication.requestInput(PROMPT_TOPIC_ITEM);
        RemoveItemFromCartRequest request =
                new RemoveItemFromCartRequest(currentUserId, itemName);
        RemoveItemFromCartResponse response = removeItemFromCartService.execute(request);
        if (response.hasErrors()) {
            response.getErrors().forEach(error -> userCommunication.informUser(error.getMessage()));
        } else {
            userCommunication.informUser(MESSAGE_ITEM_REMOVED);
        }
    }

}
