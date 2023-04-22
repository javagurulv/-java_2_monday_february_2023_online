package shop.core.services.actions.customer;

import shop.core.database.Database;
import shop.core.domain.cart.Cart;
import shop.core.domain.cart_item.CartItem;
import shop.core.domain.item.Item;
import shop.core.requests.customer.ListCartItemsRequest;
import shop.core.responses.CoreError;
import shop.core.responses.customer.ListCartItemsResponse;
import shop.core.services.cart.CartService;
import shop.core.services.validators.actions.customer.ListCartItemValidator;
import shop.core.services.validators.universal.system.DatabaseAccessValidator;
import shop.core.support.CartItemForList;
import shop.dependency_injection.DIComponent;
import shop.dependency_injection.DIDependency;

import java.math.BigDecimal;
import java.util.List;
import java.util.stream.Collectors;

@DIComponent
public class ListCartItemsService {

    @DIDependency
    private Database database;
    @DIDependency
    private ListCartItemValidator validator;
    @DIDependency
    private DatabaseAccessValidator databaseAccessValidator;
    @DIDependency
    private CartService cartService;

    public ListCartItemsResponse execute(ListCartItemsRequest request) {
        List<CoreError> errors = validator.validate(request);
        if (!errors.isEmpty()) {
            return new ListCartItemsResponse(errors);
        }
        Cart cart = databaseAccessValidator.getOpenCartByUserId(request.getUserId().getValue());
        List<CartItem> cartItems = database.accessCartItemDatabase().getAllCartItemsForCartId(cart.getId());
        List<CartItemForList> cartItemsForList = cartItems.stream()
                .map(this::createCartItemForList)
                .collect(Collectors.toList());
        BigDecimal cartTotal = cartService.getSum(cart.getUserId());
        return new ListCartItemsResponse(cartItemsForList, cartTotal);
    }

    private CartItemForList createCartItemForList(CartItem cartItem) {
        Item item = databaseAccessValidator.getItemById(cartItem.getItemId());
        return new CartItemForList(item.getName(), item.getPrice(), cartItem.getOrderedQuantity());
    }

}
