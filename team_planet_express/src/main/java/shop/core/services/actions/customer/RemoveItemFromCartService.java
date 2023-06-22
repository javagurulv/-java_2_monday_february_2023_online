package shop.core.services.actions.customer;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;
import shop.core.database.jpa.JpaCartItemRepository;
import shop.core.database.jpa.JpaItemRepository;
import shop.core.domain.Cart;
import shop.core.domain.CartItem;
import shop.core.domain.Item;
import shop.core.requests.customer.RemoveItemFromCartRequest;
import shop.core.responses.CoreError;
import shop.core.responses.customer.RemoveItemFromCartResponse;
import shop.core.services.validators.actions.customer.RemoveItemFromCartValidator;
import shop.core.services.validators.universal.system.RepositoryAccessValidator;

import java.util.List;

@Component
@Transactional
public class RemoveItemFromCartService {

    @Autowired
    private JpaItemRepository itemRepository;
    @Autowired
    private JpaCartItemRepository cartItemRepository;
    @Autowired
    private RemoveItemFromCartValidator validator;
    @Autowired
    private RepositoryAccessValidator repositoryAccessValidator;


    public RemoveItemFromCartResponse execute(RemoveItemFromCartRequest request) {
        List<CoreError> errors = validator.validate(request);
        if (!errors.isEmpty()) {
            return new RemoveItemFromCartResponse(errors);
        }
        Cart cart = repositoryAccessValidator.getOpenCartByUser(
                repositoryAccessValidator.getUserById(request.getCurrentUserId().getValue()));
        Item item = repositoryAccessValidator.getItemByName(request.getItemName());
        CartItem cartItem = repositoryAccessValidator.getCartItemByCartAndItem(cart, item);
        Integer newAvailableQuantity = item.getAvailableQuantity() + cartItem.getOrderedQuantity();
        cartItemRepository.deleteById(cartItem.getId());
        itemRepository.updateAvailableQuantity(item.getId(), newAvailableQuantity);
        return new RemoveItemFromCartResponse();
    }

}
