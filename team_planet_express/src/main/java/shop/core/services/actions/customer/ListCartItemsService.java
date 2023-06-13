package shop.core.services.actions.customer;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;
import shop.core.converters.CartItemConverter;
import shop.core.database.CartItemRepository;
import shop.core.domain.Cart;
import shop.core.domain.CartItem;
import shop.core.dtos.CartItemDto;
import shop.core.requests.customer.ListCartItemsRequest;
import shop.core.responses.CoreError;
import shop.core.responses.customer.ListCartItemsResponse;
import shop.core.services.validators.actions.customer.ListCartItemValidator;
import shop.core.services.validators.universal.system.DatabaseAccessValidator;

import java.util.List;

@Component
@Transactional
public class ListCartItemsService {

    @Autowired
    private CartItemRepository cartItemRepository;
    @Autowired
    private ListCartItemValidator validator;
    @Autowired
    private DatabaseAccessValidator databaseAccessValidator;
    @Autowired
    private CartItemConverter cartItemConverter;

    public ListCartItemsResponse execute(ListCartItemsRequest request) {
        List<CoreError> errors = validator.validate(request);
        if (!errors.isEmpty()) {
            return new ListCartItemsResponse(null, errors);
        }
        Cart cart = databaseAccessValidator.getOpenCartByUserId(request.getCurrentUserId().getValue());
        List<CartItem> cartItems = cartItemRepository.getAllCartItemsForCartId(cart.getId());
        List<CartItemDto> cartItemDtos = cartItemConverter.toCartItemDto(cartItems);
        return new ListCartItemsResponse(cartItemDtos, null);
    }

}
