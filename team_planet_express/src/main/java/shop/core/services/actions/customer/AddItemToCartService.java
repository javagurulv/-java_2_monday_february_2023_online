package shop.core.services.actions.customer;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;
import shop.core.database.jpa.JpaCartItemRepository;
import shop.core.database.jpa.JpaItemRepository;
import shop.core.domain.Cart;
import shop.core.domain.CartItem;
import shop.core.domain.Item;
import shop.core.requests.customer.AddItemToCartRequest;
import shop.core.responses.CoreError;
import shop.core.responses.customer.AddItemToCartResponse;
import shop.core.services.validators.actions.customer.AddItemToCartValidator;
import shop.core.services.validators.universal.system.RepositoryAccessValidator;

import java.util.List;
import java.util.Optional;

@Component
@Transactional
public class AddItemToCartService {

    @Autowired
    private JpaItemRepository itemRepository;
    @Autowired
    private JpaCartItemRepository cartItemRepository;
    @Autowired
    private AddItemToCartValidator validator;
    @Autowired
    private RepositoryAccessValidator repositoryAccessValidator;

    public AddItemToCartResponse execute(AddItemToCartRequest request) {
        List<CoreError> errors = validator.validate(request);
        if (!errors.isEmpty()) {
            return new AddItemToCartResponse(errors);
        }
        Cart cart = repositoryAccessValidator.getOpenCartByUser(
                repositoryAccessValidator.getUserById(request.getCurrentUserId().getValue()));
        Item item = repositoryAccessValidator.getItemByName(request.getItemName());
        Integer orderedQuantity = Integer.parseInt(request.getOrderedQuantity());
        addItemToCart(cart, item, orderedQuantity);
        changeItemAvailability(item, orderedQuantity);
        return new AddItemToCartResponse();
    }

    private void addItemToCart(Cart cart, Item item, Integer orderedQuantity) {
        Optional<CartItem> cartItem = cartItemRepository.findFirstByCartAndItem(cart, item)
                .stream()
                .findFirst();
        if (cartItem.isEmpty()) {
            cartItemRepository.save(new CartItem(cart, item, orderedQuantity));
        } else {
            Integer newCartItemQuantity = cartItem.get().getOrderedQuantity() + orderedQuantity;
            cartItemRepository.updateOrderedQuantity(cartItem.get().getId(), newCartItemQuantity);
        }
    }

    private void changeItemAvailability(Item item, Integer orderedQuantity) {
        Integer newAvailableQuantity = item.getAvailableQuantity() - orderedQuantity;
        itemRepository.updateAvailableQuantity(item.getId(), newAvailableQuantity);
    }

}
