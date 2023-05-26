package shop.core.services.actions.customer;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import shop.core.database.CartItemRepository;
import shop.core.database.ItemRepository;
import shop.core.database.Repository;
import shop.core.domain.cart.Cart;
import shop.core.domain.item.Item;
import shop.core.requests.customer.AddItemToCartRequest;
import shop.core.services.validators.actions.customer.AddItemToCartValidator;
import shop.core.services.validators.universal.system.DatabaseAccessValidator;
import shop.core.support.CurrentUserId;
import shop.matchers.CartItemMatcher;

import java.util.Collections;
import java.util.Optional;

import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
class AddItemToCartServiceTest {

    @Mock
    private Repository mockRepository;
    @Mock
    private AddItemToCartValidator mockValidator;
    @Mock
    private DatabaseAccessValidator mockDatabaseAccessValidator;
    @Mock
    private final AddItemToCartRequest mockRequest = mock(AddItemToCartRequest.class);
    @Mock
    private final CurrentUserId mockUserId = mock(CurrentUserId.class);
    @Mock
    private final ItemRepository mockItemRepository = mock(ItemRepository.class);
    @Mock
    private final CartItemRepository mockCartItemRepository = mock(CartItemRepository.class);
    @Mock
    private final Item mockItem = mock(Item.class);
    @Mock
    private final Cart mockCart = mock(Cart.class);

    @InjectMocks
    private AddItemToCartService service;

    @Test
    void shouldAddNewItemToCart() {
        when(mockValidator.validate(mockRequest)).thenReturn(Collections.emptyList());
        when(mockRequest.getCurrentUserId()).thenReturn(mockUserId);
        when(mockUserId.getValue()).thenReturn(1L);
        when(mockDatabaseAccessValidator.getOpenCartByUserId(1L)).thenReturn(mockCart);
        when(mockRequest.getItemName()).thenReturn("item name");
        when(mockDatabaseAccessValidator.getItemByName("item name")).thenReturn(mockItem);
        when(mockRequest.getOrderedQuantity()).thenReturn("10");
        when(mockCart.getId()).thenReturn(1L);
        when(mockItem.getId()).thenReturn(1L);
        when(mockRepository.accessCartItemRepository()).thenReturn(mockCartItemRepository);
        when(mockCartItemRepository.findByCartIdAndItemId(1L, 1L)).thenReturn(Optional.empty());
        when(mockRepository.accessItemRepository()).thenReturn(mockItemRepository);
        service.execute(mockRequest);
        verify(mockCartItemRepository).save(argThat(new CartItemMatcher(mockCart, mockItem, 10)));
    }

    @Test
    void shouldDecreaseAvailableQuantity() {
        when(mockValidator.validate(mockRequest)).thenReturn(Collections.emptyList());
        when(mockRequest.getCurrentUserId()).thenReturn(mockUserId);
        when(mockUserId.getValue()).thenReturn(1L);
        when(mockDatabaseAccessValidator.getOpenCartByUserId(1L)).thenReturn(mockCart);
        when(mockRequest.getItemName()).thenReturn("item name");
        when(mockDatabaseAccessValidator.getItemByName("item name")).thenReturn(mockItem);
        when(mockRequest.getOrderedQuantity()).thenReturn("10");
        when(mockRepository.accessCartItemRepository()).thenReturn(mockCartItemRepository);
        when(mockItem.getAvailableQuantity()).thenReturn(15);
        when(mockRepository.accessItemRepository()).thenReturn(mockItemRepository);
        when(mockItem.getId()).thenReturn(1L);
        service.execute(mockRequest);
        verify(mockItemRepository).changeAvailableQuantity(1L, 5);
    }

    //TODO MOAR

}
