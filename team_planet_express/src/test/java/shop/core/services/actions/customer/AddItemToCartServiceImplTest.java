package shop.core.services.actions.customer;

import org.junit.Ignore;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.data.jpa.domain.Specification;
import shop.core.database.CartItemRepository;
import shop.core.database.CartRepository;
import shop.core.database.ItemRepository;
import shop.core.domain.cart.Cart;
import shop.core.domain.cart_item.CartItem;
import shop.core.domain.item.Item;
import shop.core.domain.user.User;
import shop.core.services.actions.shared.SecurityServiceImpl;
import shop.core.services.validators.services_validators.customer.AddItemToCartValidator;
import shop.core_api.dto.cart_item.CartItemDTO;
import shop.core_api.dto.item.ItemDTO;
import shop.core_api.requests.customer.AddItemToCartRequest;

import java.math.BigDecimal;
import java.util.Collections;
import java.util.Optional;

import static org.mockito.Mockito.any;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)

@Ignore
class AddItemToCartServiceImplTest {

    @Mock
    private ItemRepository mockItemRepository;
    @Mock
    private CartItemRepository mockCartItemRepository;
    @Mock
    private CartRepository mockCartRepository;
    @Mock
    private AddItemToCartValidator mockValidator;
    @Mock
    private AddItemToCartRequest mockRequest;
    @Mock
    private Item mockItem;
    @Mock
    private Cart mockCart;
    @Mock
    private CartItem mockCartItem;
    @Mock
    private CartItemDTO mockCartItemDTO;
    @Mock
    private ItemDTO mockItemDTO;
    @Mock
    private User mockUser;
    @Mock
    private Optional<User> mockOptionalUser;
    @Mock
    private Optional<Cart> mockOptionalCart;
    @Mock
    private Optional<Item> mockOptionalItem;
    @Mock
    private Optional<CartItem> mockOptionalCartItem;

    @InjectMocks
    private AddItemToCartServiceImpl service;
    @Mock
    private SecurityServiceImpl mockSecurityService;

    @Test
    void shouldAddNewItemToCart() {
        when(mockValidator.validate(mockRequest)).thenReturn(Collections.emptyList());
        when(mockSecurityService.getAuthenticatedUserFromDB()).thenReturn(mockOptionalUser);
        when(mockItemRepository.getReferenceById(any())).thenReturn(mockItem);
        when(mockCartRepository.getReferenceById(any())).thenReturn(mockCart);
        when(mockItemRepository.findById(any())).thenReturn(Optional.of(mockItem));
        when(mockCartItemRepository.findOne(any(Specification.class))).thenReturn(mockOptionalCartItem);
        when(mockOptionalCartItem.isEmpty()).thenReturn(true);
        when(mockCartItemDTO.getItemDTO()).thenReturn(mockItemDTO);
        when(mockCartItem.getCart()).thenReturn(mockCart);
        when(mockCartItem.getItem()).thenReturn(mockItem);
        when(mockCartItemRepository.save(any(CartItem.class))).thenReturn(mockCartItem);
        when(mockRequest.getCartItemDTO()).thenReturn(mockCartItemDTO);
        when(mockOptionalUser.isPresent()).thenReturn(true);
        when(mockCart.getId()).thenReturn(1L);
        when(mockItem.getId()).thenReturn(1L);
        when(mockItem.getPrice()).thenReturn(BigDecimal.ONE);
        service.execute(mockRequest);
    }

    @Test
    void shouldDecreaseAvailableQuantity() {
        when(mockValidator.validate(mockRequest)).thenReturn(Collections.emptyList());
        when(mockSecurityService.getAuthenticatedUserFromDB()).thenReturn(mockOptionalUser);
        when(mockCartItemRepository.findOne(any(Specification.class))).thenReturn(mockOptionalCartItem);
        when(mockItemRepository.getReferenceById(any())).thenReturn(mockItem);
        when(mockCartRepository.getReferenceById(any())).thenReturn(mockCart);
        when(mockItemRepository.findById(any())).thenReturn(Optional.of(mockItem));
        when(mockOptionalCartItem.isEmpty()).thenReturn(false);
        when(mockOptionalCartItem.get()).thenReturn(mockCartItem);
        when(mockCartItemDTO.getItemDTO()).thenReturn(mockItemDTO);
        when(mockRequest.getCartItemDTO()).thenReturn(mockCartItemDTO);
        when(mockOptionalUser.isPresent()).thenReturn(true);
        when(mockItem.getPrice()).thenReturn(BigDecimal.ONE);
        when(mockItem.getId()).thenReturn(1L);
        when(mockCartItem.getCart()).thenReturn(mockCart);
        when(mockCartItem.getItem()).thenReturn(mockItem);
        when(mockItem.getAvailableQuantity()).thenReturn(15);
        service.execute(mockRequest);
    }

}
