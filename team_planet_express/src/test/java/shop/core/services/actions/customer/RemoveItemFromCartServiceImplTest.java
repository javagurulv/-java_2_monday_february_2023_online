package shop.core.services.actions.customer;

import org.junit.Ignore;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import shop.core.database.CartItemRepository;
import shop.core.database.ItemRepository;
import shop.core.domain.cart.Cart;
import shop.core.domain.cart_item.CartItem;
import shop.core.domain.item.Item;
import shop.core.domain.user.User;
import shop.core.services.actions.shared.SecurityServiceImpl;
import shop.core.services.validators.actions.customer.RemoveItemFromCartValidator;
import shop.core.services.validators.universal.system.DatabaseAccessValidator;
import shop.core_api.requests.customer.RemoveItemFromCartRequest;

import java.util.List;
import java.util.Optional;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)

@Ignore
class RemoveItemFromCartServiceImplTest {

    @Mock
    private ItemRepository mockItemRepository;
    @Mock
    private CartItemRepository mockCartItemRepository;
    @Mock
    private RemoveItemFromCartValidator mockValidator;
    @Mock
    private DatabaseAccessValidator mockDatabaseAccessValidator;
    @Mock
    private RemoveItemFromCartRequest mockRequest;
    @Mock
    private SecurityServiceImpl mockSecurityService;
    @Mock
    private Cart mockCart;
    @Mock
    private Item mockItem;
    @Mock
    private CartItem mockCartItem;

    @InjectMocks
    private RemoveItemFromCartServiceImpl service;

    @BeforeEach
    void initMock() {
        when(mockValidator.validate(any())).thenReturn(List.of());
        //when(mockRequest.getCurrentUserId()).thenReturn(mockCurrentUserId);
    }

    @Test
    void shouldDeleteFromCart() {
        when(mockSecurityService.getAuthenticatedUserFromDB()).thenReturn(Optional.of(new User()));
        when(mockDatabaseAccessValidator.getOpenCartByUserId(any())).thenReturn(mockCart);
        when(mockRequest.getItemName()).thenReturn("Item");
        when(mockDatabaseAccessValidator.getItemByName("Item")).thenReturn(mockItem);
        when(mockCart.getId()).thenReturn(1L);
        when(mockItem.getId()).thenReturn(2L);
        when(mockCartItem.getId()).thenReturn(3L);
        when(mockDatabaseAccessValidator.getCartItemByCartIdAndItemId(1L, 2L)).thenReturn(mockCartItem);
        service.execute(mockRequest);
        verify(mockCartItemRepository).deleteByID(3L);
    }

    @Test
    void shouldReturnAvailableQuantity() {
        when(mockSecurityService.getAuthenticatedUserFromDB()).thenReturn(Optional.of(new User()));
        when(mockDatabaseAccessValidator.getOpenCartByUserId(any())).thenReturn(mockCart);
        when(mockRequest.getItemName()).thenReturn("Item");
        when(mockDatabaseAccessValidator.getItemByName("Item")).thenReturn(mockItem);
        when(mockCart.getId()).thenReturn(1L);
        when(mockItem.getId()).thenReturn(2L);
        when(mockItem.getAvailableQuantity()).thenReturn(10);
        when(mockCartItem.getId()).thenReturn(3L);
        when(mockCartItem.getOrderedQuantity()).thenReturn(11);
        when(mockDatabaseAccessValidator.getCartItemByCartIdAndItemId(1L, 2L)).thenReturn(mockCartItem);
        service.execute(mockRequest);
        verify(mockItemRepository).changeAvailableQuantity(2L, 21);
    }

}
