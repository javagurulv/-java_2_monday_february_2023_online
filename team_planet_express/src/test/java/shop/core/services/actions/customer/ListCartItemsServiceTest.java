package shop.core.services.actions.customer;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import shop.core.database.CartItemRepository;
import shop.core.database.Repository;
import shop.core.domain.cart.Cart;
import shop.core.domain.cart_item.CartItem;
import shop.core.domain.item.Item;
import shop.core.requests.customer.ListCartItemsRequest;
import shop.core.responses.customer.ListCartItemsResponse;
import shop.core.services.cart.CartService;
import shop.core.services.validators.actions.customer.ListCartItemValidator;
import shop.core.services.validators.universal.system.DatabaseAccessValidator;
import shop.core.support.CurrentUserId;

import java.math.BigDecimal;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.anyLong;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
class ListCartItemsServiceTest {

    @Mock
    private Repository mockRepository;
    @Mock
    private CartItemRepository mockCartItemRepository;
    @Mock
    private ListCartItemsRequest mockRequest;
    @Mock
    private CurrentUserId mockCurrentUserId;
    @Mock
    private ListCartItemValidator mockValidator;
    @Mock
    private DatabaseAccessValidator mockDatabaseAccessValidator;
    @Mock
    private CartService mockCartService;
    @Mock
    private Cart mockCart;
    @Mock
    private CartItem mockCartItem;
    @Mock
    private Item mockItem;

    @InjectMocks
    private ListCartItemsService service;

    @BeforeEach
    void initMock() {
        when(mockRepository.accessCartItemRepository()).thenReturn(mockCartItemRepository);
        when(mockRequest.getCurrentUserId()).thenReturn(mockCurrentUserId);
    }

    @Test
    void shouldReturnListCartItem() {
        when(mockDatabaseAccessValidator.getOpenCartByUserId(any())).thenReturn(mockCart);
        when(mockCartItemRepository.getAllCartItemsForCartId(anyLong())).thenReturn(List.of(mockCartItem, mockCartItem));
        when(mockCartItem.getItem()).thenReturn(mockItem);
        when(mockDatabaseAccessValidator.getItemById(anyLong())).thenReturn(mockItem);
        ListCartItemsResponse response = service.execute(mockRequest);
        assertEquals(response.getCartItemsForList().size(), 2);
    }

    @Test
    void shouldReturnSum() {
        when(mockDatabaseAccessValidator.getOpenCartByUserId(any())).thenReturn(mockCart);
        when(mockCartItemRepository.getAllCartItemsForCartId(anyLong())).thenReturn(List.of(mockCartItem, mockCartItem));
        when(mockDatabaseAccessValidator.getItemById(anyLong())).thenReturn(mockItem);
        when(mockCartItem.getItem()).thenReturn(mockItem);
        when(mockCart.getId()).thenReturn(1L);
        when(mockCartService.getSum(1L)).thenReturn(BigDecimal.valueOf(1));
        ListCartItemsResponse response = service.execute(mockRequest);
        assertEquals(response.getCartTotal(), BigDecimal.valueOf(1));
    }
}
