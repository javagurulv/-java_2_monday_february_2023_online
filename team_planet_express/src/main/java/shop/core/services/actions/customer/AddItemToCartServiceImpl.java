package shop.core.services.actions.customer;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import shop.core.database.CartItemRepository;
import shop.core.database.CartRepository;
import shop.core.database.ItemRepository;
import shop.core.database.specifications.CartItemSpecs;
import shop.core.domain.cart.Cart;
import shop.core.domain.cart_item.CartItem;
import shop.core.domain.cart_item.CartItemConverter;
import shop.core.domain.cart_item.CartItem_;
import shop.core.domain.item.Item;
import shop.core.domain.user.User;
import shop.core.services.validators.services_validators.customer.AddItemToCartValidator;
import shop.core_api.dto.cart_item.CartItemDTO;
import shop.core_api.entry_point.customer.AddItemToCartService;
import shop.core_api.entry_point.shared.SecurityService;
import shop.core_api.requests.customer.AddItemToCartRequest;
import shop.core_api.responses.CoreError;
import shop.core_api.responses.customer.AddItemToCartResponse;

import java.util.List;
import java.util.Optional;

import static shop.core.database.specifications.CartSpecs.findOpenCartForUser;

@Service
@Transactional
public class AddItemToCartServiceImpl implements AddItemToCartService {

    @Autowired
    private CartItemRepository cartItemRepository;
    @Autowired
    private ItemRepository itemRepository;
    @Autowired
    private CartRepository cartRepository;
    @Autowired
    private AddItemToCartValidator validator;
    @Autowired
    private SecurityService securityService;

    public AddItemToCartResponse execute(AddItemToCartRequest request) {
        List<CoreError> errors = validator.validate(request);
        AddItemToCartResponse addItemToCartResponse;
        if (!errors.isEmpty()) {
            addItemToCartResponse = new AddItemToCartResponse(errors);
            return addItemToCartResponse;
        } else {
            Optional<User> user = securityService.getAuthenticatedUserFromDB();
            CartItem cartItem;
            if (user.isPresent()) {
                CartItemDTO cartItemDTO = request.getCartItemDTO();
                if (cartItemDTO.getCartId() == null) {
                    Cart cart = cartRepository.findOne(findOpenCartForUser(user.get())).get();
                    cartItemDTO.setCartId(cart.getId());
                }
                cartItem = addItemToCart(cartItemDTO);
            } else {
                cartItem = addItemToLocalCart();
            }
            addItemToCartResponse = new AddItemToCartResponse(cartItem);
        }
        return addItemToCartResponse;
    }

    private CartItem addItemToLocalCart() {
        return null;
    }

    private CartItem addItemToCart(CartItemDTO cartItemDTO) {
        Item item = itemRepository.getReferenceById(cartItemDTO.getItemDTO().getId());
        Cart cart = cartRepository.getReferenceById(cartItemDTO.getCartId());
        Optional<CartItem> optionalCartItem = cartItemRepository.findOne(
                CartItemSpecs.findBy(CartItem_.CART, cart)
                        .and(CartItemSpecs.findBy(CartItem_.ITEM, item))
        );
        CartItem cartItem;
        if (optionalCartItem.isEmpty()) {
            cartItem = cartItemRepository.save(CartItemConverter.toCartItem(cartItemDTO));
        } else {
            Integer newCartItemQuantity = optionalCartItem.get().getOrderedQuantity() + cartItemDTO.getOrderedQuantity();
            optionalCartItem.get().setOrderedQuantity(newCartItemQuantity);
            cartItem = optionalCartItem.get();
        }
        cartItem.setItem(itemRepository.findById(item.getId()).get());
        return cartItem;
    }


}
