package shop.console_ui.actions.customer;

import shop.console_ui.UserCommunication;
import shop.console_ui.actions.UIAction;
import shop.core.domain.user.UserRole;
import shop.core.requests.customer.ListCartItemsRequest;
import shop.core.responses.customer.ListCartItemsResponse;
import shop.core.services.actions.customer.ListCartItemsService;
import shop.core.support.CartItemForList;
import shop.core.support.CurrentUserId;
import shop.dependency_injection.DIComponent;
import shop.dependency_injection.DIDependency;

import java.math.BigDecimal;

@DIComponent
public class ListCartItemsUIAction extends UIAction {

    private static final String ACTION_NAME = "List cart items";
    private static final int ACCESS_NUMBER = UserRole.getAccessNumber(UserRole.GUEST, UserRole.CUSTOMER);

    private static final String HEADER_TEXT = "Cart items:";
    private static final String MESSAGE_CART_IS_EMPTY = "Your cart is empty.";
    private static final String MESSAGE_CART_TOTAL = "Your cart total is: ";

    @DIDependency
    private ListCartItemsService listCartItemsService;
    @DIDependency
    private CurrentUserId currentUserId;
    @DIDependency
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
        if (response.getCartItemsForList().isEmpty()) {
            userCommunication.informUser(MESSAGE_CART_IS_EMPTY);
        } else {
            response.getCartItemsForList()
                    .forEach(cartItemForList -> userCommunication.informUser(getCartItemString(cartItemForList)));
            BigDecimal cartTotal = response.getCartTotal();
            userCommunication.informUser(MESSAGE_CART_TOTAL + cartTotal);
        }
    }

    private String getCartItemString(CartItemForList cartItemForList) {
        return cartItemForList.getItemName() +
                ", price: " + cartItemForList.getPrice() +
                ", ordered quantity: " + cartItemForList.getOrderedQuantity();
    }

}
