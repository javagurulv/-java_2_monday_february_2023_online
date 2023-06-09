package shop.core.services.actions.customer;

import org.junit.Ignore;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import shop.core.database.CartItemRepository;
import shop.core.domain.cart.Cart;
import shop.core.domain.cart_item.CartItem;
import shop.core.domain.item.Item;
import shop.core.domain.user.User;
import shop.core.services.actions.shared.SecurityServiceImpl;
import shop.core.services.cart.CartService;
import shop.core.services.validators.universal.system.DatabaseAccessValidator;
import shop.core_api.requests.customer.ListCartItemsRequest;
import shop.core_api.responses.customer.ListCartItemsResponse;

import java.math.BigDecimal;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.anyLong;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)

@Ignore
class ListCartItemsServiceImplTest {

    @Mock
    private CartItemRepository mockCartItemRepository;
    @Mock
    private DatabaseAccessValidator mockDatabaseAccessValidator;
    @Mock
    private CartService mockCartService;
    @Mock
    private ListCartItemsRequest mockRequest;
    @Mock
    private SecurityServiceImpl mockSecurityService;
    @Mock
    private Cart mockCart;
    @Mock
    private CartItem mockCartItem;
    @Mock
    private Item mockItem;

    @InjectMocks
    private ListCartItemsServiceImpl service;

    @BeforeEach
    void initMock() {
        when(mockSecurityService.getAuthenticatedUserFromDB()).thenReturn(Optional.of(new User()));
        when(mockCartItem.getCart()).thenReturn(mockCart);
        when(mockCartItem.getItem()).thenReturn(mockItem);
        when(mockItem.getId()).thenReturn(1L);
        when(mockCart.getId()).thenReturn(1L);
        when(mockItem.getPrice()).thenReturn(BigDecimal.valueOf(1));
    }

    @Test
    void shouldReturnListCartItem() {
        when(mockDatabaseAccessValidator.getOpenCartByUserId(any())).thenReturn(mockCart);
        when(mockCartItemRepository.getAllCartItemsForCartId(anyLong())).thenReturn(List.of(mockCartItem, mockCartItem));
        ListCartItemsResponse response = service.execute(mockRequest);
        assertEquals(response.getCartItemsDTO().size(), 2);
    }

    @Test
    void shouldReturnSum() {
        when(mockDatabaseAccessValidator.getOpenCartByUserId(any())).thenReturn(mockCart);
        when(mockCartItemRepository.getAllCartItemsForCartId(anyLong())).thenReturn(List.of(mockCartItem, mockCartItem));
        when(mockCartService.getSum(1L)).thenReturn(BigDecimal.valueOf(1));
        ListCartItemsResponse response = service.execute(mockRequest);
        assertEquals(response.getCartTotal(), BigDecimal.valueOf(1));
    }
}
