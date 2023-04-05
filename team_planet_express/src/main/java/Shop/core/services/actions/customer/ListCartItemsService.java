package Shop.core.services.actions.customer;

import Shop.core.database.Database;
import Shop.core.domain.cart.Cart;
import Shop.core.domain.cart_item.CartItem;
import Shop.core.requests.customer.ListCartItemsRequest;
import Shop.core.responses.CoreError;
import Shop.core.responses.customer.ListCartItemsResponse;
import Shop.core.services.cart.CartService;
import Shop.core.services.exception.ServiceMissingDataException;
import Shop.core.services.validators.actions.customer.ListCartItemValidator;

import java.math.BigDecimal;
import java.util.List;

public class ListCartItemsService {

    private final Database database;
    private final ListCartItemValidator validator;

    //TODO constructorize aka mockableize
    //TODO this should not be here to begin with..
    private final CartService cartService;

    public ListCartItemsService(Database database, ListCartItemValidator validator) {
        this.database = database;
        this.validator = validator;
        this.cartService = new CartService(database);
    }

    public ListCartItemsResponse execute(ListCartItemsRequest request) {
        List<CoreError> errors = validator.validate(request);
        if (!errors.isEmpty()) {
            return new ListCartItemsResponse(errors);
        }
        Cart cart = getOpenCartForUserId(request.getUserId().getValue());
        List<CartItem> cartItems = database.accessCartItemDatabase().getAllCartItemsForCartId(cart.getId());
        BigDecimal cartTotal = cartService.getSum(cart.getUserId());
        return new ListCartItemsResponse(cartItems, cartTotal);
    }

    //TODO yeet, duplicate
    private Cart getOpenCartForUserId(Long userId) {
        return database.accessCartDatabase().findOpenCartForUserId(userId)
                .orElseThrow(ServiceMissingDataException::new);
    }

}
