package shop.core.services.actions.customer;

import org.junit.Ignore;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import shop.core.database.CartItemRepository;
import shop.core.database.CartRepository;
import shop.core.domain.cart.Cart;
import shop.core.domain.cart_item.CartItem;
import shop.core.domain.item.Item;
import shop.core.domain.user.User;
import shop.core.requests.customer.ListCartItemsRequest;
import shop.core.responses.customer.ListCartItemsResponse;
import shop.core.services.actions.shared.SecurityService;
import shop.core.services.cart.CartService;
import shop.core.services.validators.actions.customer.ListCartItemValidator;
import shop.core.services.validators.universal.system.DatabaseAccessValidator;

import java.math.BigDecimal;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.anyLong;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)

@Ignore
class ListCartItemsServiceTest {

    @Mock
    private CartItemRepository mockCartItemRepository;
    @Mock
    private CartRepository mockCartRepository;
    @Mock
    private ListCartItemValidator mockValidator;
    @Mock
    private DatabaseAccessValidator mockDatabaseAccessValidator;
    @Mock
    private CartService mockCartService;
    @Mock
    private ListCartItemsRequest mockRequest;
    @Mock
    private SecurityService mockSecurityService;
    @Mock
    private Cart mockCart;
    @Mock
    private CartItem mockCartItem;
    @Mock
    private Optional<Cart> mockOptionalCart;
    @Mock
    private Item mockItem;

    @InjectMocks
    private ListCartItemsService service;

    @BeforeEach
    void initMock() {
        when(mockSecurityService.getAuthenticatedUserFromDB()).thenReturn(Optional.of(new User()));
    }

    @Test
    void shouldReturnListCartItem() {
        when(mockDatabaseAccessValidator.getOpenCartByUserId(any())).thenReturn(mockCart);
        when(mockCartItemRepository.getAllCartItemsForCartId(anyLong())).thenReturn(List.of(mockCartItem, mockCartItem));
        ListCartItemsResponse response = service.execute(mockRequest);
        assertEquals(response.getCartItems().size(), 2);
    }

    @Test
    void shouldReturnSum() {
        when(mockDatabaseAccessValidator.getOpenCartByUserId(any())).thenReturn(mockCart);
        when(mockCartItemRepository.getAllCartItemsForCartId(anyLong())).thenReturn(List.of(mockCartItem, mockCartItem));
        when(mockCart.getId()).thenReturn(1L);
        when(mockCartService.getSum(1L)).thenReturn(BigDecimal.valueOf(1));
        ListCartItemsResponse response = service.execute(mockRequest);
        assertEquals(response.getCartTotal(), BigDecimal.valueOf(1));
    }
}
