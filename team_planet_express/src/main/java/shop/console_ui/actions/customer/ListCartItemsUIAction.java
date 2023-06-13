package shop.console_ui.actions.customer;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import shop.console_ui.UserCommunication;
import shop.console_ui.actions.UIAction;
import shop.core.dtos.CartItemDto;
import shop.core.enums.UserRole;
import shop.core.requests.customer.ListCartItemsRequest;
import shop.core.responses.customer.ListCartItemsResponse;
import shop.core.services.actions.customer.ListCartItemsService;
import shop.core.support.CurrentUserId;

@Component
public class ListCartItemsUIAction extends UIAction {

    private static final String ACTION_NAME = "List cart items";
    private static final int ACCESS_NUMBER = UserRole.getAccessNumber(UserRole.GUEST, UserRole.CUSTOMER);

    private static final String HEADER_TEXT = "Cart items:";
    private static final String MESSAGE_CART_IS_EMPTY = "Your cart is empty.";

    @Autowired
    private ListCartItemsService listCartItemsService;
    @Autowired
    private CurrentUserId currentUserId;
    @Autowired
    private UserCommunication userCommunication;

    public ListCartItemsUIAction() {
        super(ACTION_NAME, ACCESS_NUMBER);
    }

    @Override
    public void execute() {
        userCommunication.informUser(HEADER_TEXT);
        ListCartItemsRequest request = new ListCartItemsRequest(currentUserId);
        ListCartItemsResponse response = listCartItemsService.execute(request);
        if (response.hasErrors()) {
            response.getErrors().forEach(error -> userCommunication.informUser(error.getMessage()));
        } else {
            printCartItems(response);
        }
    }

    private void printCartItems(ListCartItemsResponse response) {
        if (response.getCartItems().isEmpty()) {
            userCommunication.informUser(MESSAGE_CART_IS_EMPTY);
        } else {
            response.getCartItems()
                    .forEach(cartItem -> userCommunication.informUser(getCartItemString(cartItem)));
        }
    }

    private String getCartItemString(CartItemDto cartItem) {
        return cartItem.getItemName() +
                ", price: " + cartItem.getItemPrice() +
                ", ordered quantity: " + cartItem.getOrderedQuantity();
    }

}
