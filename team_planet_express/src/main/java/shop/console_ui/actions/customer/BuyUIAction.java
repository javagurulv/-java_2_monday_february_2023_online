package shop.console_ui.actions.customer;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import shop.console_ui.UserCommunication;
import shop.console_ui.actions.UIAction;
import shop.core.enums.UserRole;
import shop.core.requests.customer.BuyRequest;
import shop.core.responses.customer.BuyResponse;
import shop.core.services.actions.customer.BuyService;
import shop.core.support.CurrentUserId;

@Component
public class BuyUIAction extends UIAction {

    private static final String ACTION_NAME = "Buy items in the cart";
    private static final int ACCESS_NUMBER = UserRole.getAccessNumber(UserRole.GUEST, UserRole.CUSTOMER);

    private static final String MESSAGE_CART_IS_CLOSED = "Your cart is closed now.";

    @Autowired
    private BuyService buyService;
    @Autowired
    private CurrentUserId currentUserId;
    @Autowired
    private UserCommunication userCommunication;

    public BuyUIAction() {
        super(ACTION_NAME, ACCESS_NUMBER);
    }

    @Override
    public void execute() {
        BuyRequest request = new BuyRequest(currentUserId);
        BuyResponse response = buyService.execute(request);
        if (response.hasErrors()) {
            response.getErrors().forEach(error -> userCommunication.informUser(error.getMessage()));
        } else {
            userCommunication.informUser(MESSAGE_CART_IS_CLOSED);
        }
    }

}
