package core.services.actions.customer;

import core.database.CartItemDatabase;
import core.database.Database;
import core.database.ItemDatabase;
import core.domain.cart.Cart;
import core.domain.cart_item.CartItem;
import core.domain.item.Item;
import core.requests.customer.AddItemToCartRequest;
import core.services.validators.actions.customer.AddItemToCartValidator;
import core.services.validators.universal.system.DatabaseAccessValidator;
import core.support.MutableLong;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.Collections;
import java.util.Optional;

import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
class AddItemToCartServiceTest {

    @Mock private Database mockDatabase;
    @Mock private AddItemToCartValidator mockValidator;
    @Mock private DatabaseAccessValidator mockDatabaseAccessValidator;
    @Mock private AddItemToCartRequest mockRequest = mock(AddItemToCartRequest.class);
    @Mock private MutableLong mockUserId = mock(MutableLong.class);
    @Mock private ItemDatabase mockItemDatabase = mock(ItemDatabase.class);
    @Mock private CartItemDatabase mockCartItemDatabase = mock(CartItemDatabase.class);
    @Mock private Item mockItem = mock(Item.class);
    @Mock private Cart mockCart = mock(Cart.class);

    @InjectMocks private AddItemToCartService service;

    @Test
    void shouldAddNewItemToCart() {
        when(mockValidator.validate(mockRequest)).thenReturn(Collections.emptyList());
        when(mockRequest.getUserId()).thenReturn(mockUserId);
        when(mockUserId.getValue()).thenReturn(1L);
        when(mockDatabaseAccessValidator.getOpenCartByUserId(1L)).thenReturn(mockCart);
        when(mockRequest.getItemName()).thenReturn("item name");
        when(mockDatabaseAccessValidator.getItemByName("item name")).thenReturn(mockItem);
        when(mockRequest.getOrderedQuantity()).thenReturn("10");
        when(mockCart.getId()).thenReturn(1L);
        when(mockItem.getId()).thenReturn(1L);
        when(mockDatabase.accessCartItemDatabase()).thenReturn(mockCartItemDatabase);
        when(mockCartItemDatabase.findByCartIdAndItemId(1L, 1L)).thenReturn(Optional.empty());
        when(mockDatabase.accessItemDatabase()).thenReturn(mockItemDatabase);
        service.execute(mockRequest);
        verify(mockCartItemDatabase).save(new CartItem(1L, 1L, 10));
    }

    @Test
    void shouldDecreaseAvailableQuantity() {
        when(mockValidator.validate(mockRequest)).thenReturn(Collections.emptyList());
        when(mockRequest.getUserId()).thenReturn(mockUserId);
        when(mockUserId.getValue()).thenReturn(1L);
        when(mockDatabaseAccessValidator.getOpenCartByUserId(1L)).thenReturn(mockCart);
        when(mockRequest.getItemName()).thenReturn("item name");
        when(mockDatabaseAccessValidator.getItemByName("item name")).thenReturn(mockItem);
        when(mockRequest.getOrderedQuantity()).thenReturn("10");
        when(mockDatabase.accessCartItemDatabase()).thenReturn(mockCartItemDatabase);
        when(mockItem.getAvailableQuantity()).thenReturn(15);
        when(mockDatabase.accessItemDatabase()).thenReturn(mockItemDatabase);
        when(mockItem.getId()).thenReturn(1L);
        service.execute(mockRequest);
        verify(mockItemDatabase).changeAvailableQuantity(1L, 5);
    }

    //TODO MOAR

}