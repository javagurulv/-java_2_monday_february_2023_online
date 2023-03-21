package console_ui.actions.shop;

import console_ui.UserCommunication;
import console_ui.actions.UIAction;
import domain.user.UserRole;
import services.actions.shop.BuyService;
import services.exception.CartIsEmptyException;
import services.exception.NoOpenCartException;

public class BuyUIAction extends UIAction {

    private static final String ACTION_NAME = "Buy items in the cart";
    private static final int ACCESS_NUMBER = UserRole.getAccessNumber(UserRole.GUEST, UserRole.CUSTOMER);

    private static final String MESSAGE_CART_IS_CLOSED = "Your cart is closed now.";

    private final BuyService buyService;
    private final UserCommunication userCommunication;

    public BuyUIAction(BuyService buyService, UserCommunication userCommunication) {
        super(ACTION_NAME, ACCESS_NUMBER);
        this.buyService = buyService;
        this.userCommunication = userCommunication;
    }

    @Override
    public void execute() {
        try {
            buyService.execute();
            userCommunication.informUser(MESSAGE_CART_IS_CLOSED);
        } catch (NoOpenCartException | CartIsEmptyException exception) {
            userCommunication.informUser(exception.getMessage());
        }
    }

}
