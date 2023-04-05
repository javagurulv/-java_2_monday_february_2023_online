package Shop.console_ui.actions.customer;

import Shop.core.domain.user.UserRole;
import Shop.core.requests.customer.BuyRequest;
import Shop.core.responses.customer.BuyResponse;
import Shop.core.support.MutableLong;
import Shop.console_ui.UserCommunication;
import Shop.console_ui.actions.UIAction;
import Shop.core.services.actions.customer.BuyService;

public class BuyUIAction extends UIAction {

    private static final String ACTION_NAME = "Buy items in the cart";
    private static final int ACCESS_NUMBER = UserRole.getAccessNumber(UserRole.GUEST, UserRole.CUSTOMER);

    private static final String MESSAGE_CART_IS_CLOSED = "Your cart is closed now.";

    private final BuyService buyService;
    private final MutableLong currentUserId;
    private final UserCommunication userCommunication;

    public BuyUIAction(BuyService buyService, MutableLong currentUserId, UserCommunication userCommunication) {
        super(ACTION_NAME, ACCESS_NUMBER);
        this.buyService = buyService;
        this.currentUserId = currentUserId;
        this.userCommunication = userCommunication;
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