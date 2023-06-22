package shop.core.services.actions.customer;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import shop.core.database.jpa.JpaCartItemRepository;
import shop.core.database.jpa.JpaItemRepository;
import shop.core.domain.Cart;
import shop.core.domain.Item;
import shop.core.domain.User;
import shop.core.requests.customer.AddItemToCartRequest;
import shop.core.services.validators.actions.customer.AddItemToCartValidator;
import shop.core.services.validators.universal.system.RepositoryAccessValidator;
import shop.core.support.CurrentUserId;
import shop.matchers.CartItemMatcher;

import java.util.Collections;
import java.util.Optional;

import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
class AddItemToCartServiceTest {

    @Mock
    private JpaItemRepository mockJpaItemRepository;
    @Mock
    private JpaCartItemRepository mockJpaCartItemRepository;
    @Mock
    private AddItemToCartValidator mockValidator;
    @Mock
    private RepositoryAccessValidator mockRepositoryAccessValidator;
    @Mock
    private AddItemToCartRequest mockRequest;
    @Mock
    private CurrentUserId mockUserId;
    @Mock
    private User mockUser;
    @Mock
    private Item mockItem;
    @Mock
    private Cart mockCart;

    @InjectMocks
    private AddItemToCartService service;

    @Test
    void shouldAddNewItemToCart() {
        when(mockValidator.validate(mockRequest)).thenReturn(Collections.emptyList());
        when(mockRepositoryAccessValidator.getUserById(anyLong())).thenReturn(mockUser);
        when(mockRequest.getCurrentUserId()).thenReturn(mockUserId);
        when(mockRepositoryAccessValidator.getOpenCartByUser(mockUser)).thenReturn(mockCart);
        when(mockRequest.getItemName()).thenReturn("item name");
        when(mockRepositoryAccessValidator.getItemByName("item name")).thenReturn(mockItem);
        when(mockRequest.getOrderedQuantity()).thenReturn("10");
        when(mockJpaCartItemRepository.findFirstByCartAndItem(mockCart, mockItem)).thenReturn(Optional.empty());
        service.execute(mockRequest);
        verify(mockJpaCartItemRepository).save(argThat(new CartItemMatcher(mockCart, mockItem, 10)));
    }

    @Test
    void shouldDecreaseAvailableQuantity() {
        when(mockValidator.validate(mockRequest)).thenReturn(Collections.emptyList());
        when(mockRepositoryAccessValidator.getUserById(anyLong())).thenReturn(mockUser);
        when(mockRequest.getCurrentUserId()).thenReturn(mockUserId);
        when(mockRepositoryAccessValidator.getOpenCartByUser(mockUser)).thenReturn(mockCart);
        when(mockRequest.getItemName()).thenReturn("item name");
        when(mockRepositoryAccessValidator.getItemByName("item name")).thenReturn(mockItem);
        when(mockRequest.getOrderedQuantity()).thenReturn("10");
        when(mockItem.getAvailableQuantity()).thenReturn(15);
        when(mockItem.getId()).thenReturn(1L);
        service.execute(mockRequest);
        verify(mockJpaItemRepository).updateAvailableQuantity(1L, 5);
    }

}
