package shop.core.services.actions.customer;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import shop.core.database.jpa.JpaCartItemRepository;
import shop.core.database.jpa.JpaItemRepository;
import shop.core.domain.Cart;
import shop.core.domain.CartItem;
import shop.core.domain.Item;
import shop.core.requests.customer.RemoveItemFromCartRequest;
import shop.core.services.validators.actions.customer.RemoveItemFromCartValidator;
import shop.core.services.validators.universal.system.RepositoryAccessValidator;
import shop.core.support.CurrentUserId;

import java.util.List;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
class RemoveItemFromCartServiceTest {

    @Mock
    private JpaItemRepository mockJpaItemRepository;
    @Mock
    private JpaCartItemRepository mockJpaCartItemRepository;
    @Mock
    private RemoveItemFromCartValidator mockValidator;
    @Mock
    private RepositoryAccessValidator mockRepositoryAccessValidator;
    @Mock
    private RemoveItemFromCartRequest mockRequest;
    @Mock
    private CurrentUserId mockCurrentUserId;
    @Mock
    private Cart mockCart;
    @Mock
    private Item mockItem;
    @Mock
    private CartItem mockCartItem;

    @InjectMocks
    private RemoveItemFromCartService service;

    @BeforeEach
    void initMock() {
        when(mockValidator.validate(any())).thenReturn(List.of());
        when(mockRequest.getCurrentUserId()).thenReturn(mockCurrentUserId);
    }

    @Test
    void shouldDeleteFromCart() {
        when(mockRepositoryAccessValidator.getOpenCartByUser(any())).thenReturn(mockCart);
        when(mockRequest.getItemName()).thenReturn("Item");
        when(mockRepositoryAccessValidator.getItemByName("Item")).thenReturn(mockItem);
        when(mockCartItem.getId()).thenReturn(3L);
        when(mockRepositoryAccessValidator.getCartItemByCartAndItem(mockCart, mockItem)).thenReturn(mockCartItem);
        service.execute(mockRequest);
        verify(mockJpaCartItemRepository).deleteById(3L);
    }

    @Test
    void shouldReturnAvailableQuantity() {
        when(mockRepositoryAccessValidator.getOpenCartByUser(any())).thenReturn(mockCart);
        when(mockRequest.getItemName()).thenReturn("Item");
        when(mockRepositoryAccessValidator.getItemByName("Item")).thenReturn(mockItem);
        when(mockItem.getAvailableQuantity()).thenReturn(10);
        when(mockCartItem.getId()).thenReturn(3L);
        when(mockCartItem.getOrderedQuantity()).thenReturn(11);
        when(mockRepositoryAccessValidator.getCartItemByCartAndItem(mockCart, mockItem)).thenReturn(mockCartItem);
        when(mockItem.getId()).thenReturn(2L);
        service.execute(mockRequest);
        verify(mockJpaItemRepository).updateAvailableQuantity(2L, 21);
    }

}
