package shop.core.services.actions.customer;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Disabled;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import shop.core.database.CartItemRepository;
import shop.core.database.Repository;
import shop.core.database.ItemRepository;
import shop.core.domain.cart.Cart;
import shop.core.domain.cart_item.CartItem;
import shop.core.domain.item.Item;
import shop.core.requests.customer.RemoveItemFromCartRequest;
import shop.core.services.validators.actions.customer.RemoveItemFromCartValidator;
import shop.core.services.validators.universal.system.DatabaseAccessValidator;
import shop.core.support.CurrentUser;

import java.util.List;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
class RemoveItemFromCartServiceTest {

    @Mock
    private Repository mockRepository;
    @Mock
    private RemoveItemFromCartValidator mockValidator;
    @Mock
    private DatabaseAccessValidator mockDatabaseAccessValidator;
    @Mock
    private RemoveItemFromCartRequest mockRequest;
    @Mock
    private CartItemRepository mockCartItemRepository;
    @Mock
    private ItemRepository mockItemRepository;
    @Mock
    private CurrentUser mockCurrentUser;
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
        when(mockRepository.accessCartItemDatabase()).thenReturn(mockCartItemRepository);
        when(mockRepository.accessItemDatabase()).thenReturn(mockItemRepository);
        when(mockValidator.validate(any())).thenReturn(List.of());
        when(mockRequest.getUserId()).thenReturn(mockCurrentUser);
    }

    @Test
    void shouldDeleteFromCart() {

        when(mockDatabaseAccessValidator.getOpenCartByUserId(any())).thenReturn(mockCart);
        when(mockRequest.getItemName()).thenReturn("Item");
        when(mockDatabaseAccessValidator.getItemByName("Item")).thenReturn(mockItem);
        when(mockCartItem.getId()).thenReturn(3L);
        when(mockDatabaseAccessValidator.getCartItemByCartIdAndItemId(mockCart, mockItem)).thenReturn(mockCartItem);
        service.execute(mockRequest);

        verify(mockCartItemRepository).deleteByID(3L);
    }

    @Disabled
    @Test
    void shouldReturnAvailableQuantity() {
        //TODO ffffffffffffffffffffffffffffffff
        when(mockDatabaseAccessValidator.getOpenCartByUserId(any())).thenReturn(mockCart);
        when(mockRequest.getItemName()).thenReturn("Item");
        when(mockDatabaseAccessValidator.getItemByName("Item")).thenReturn(mockItem);
        when(mockItem.getAvailableQuantity()).thenReturn(10);
        when(mockCartItem.getId()).thenReturn(3L);
        when(mockCartItem.getOrderedQuantity()).thenReturn(11);
        when(mockDatabaseAccessValidator.getCartItemByCartIdAndItemId(mockCart, mockItem)).thenReturn(mockCartItem);
        service.execute(mockRequest);

        verify(mockItemRepository).changeAvailableQuantity(2L, 21);

    }
}
