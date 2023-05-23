package shop.core.services.validators.universal.system;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import shop.core.database.*;
import shop.core.domain.cart.Cart;
import shop.core.domain.cart_item.CartItem;
import shop.core.domain.item.Item;
import shop.core.domain.user.User;
import shop.core.services.exception.ServiceMissingDataException;

import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
class RepositoryAccessValidatorTest {

    @Mock
    private Repository mockRepository;
    @Mock
    private UserRepository mockUserRepository;
    @Mock
    private ItemRepository mockItemRepository;
    @Mock
    private CartRepository mockCartRepository;
    @Mock
    private CartItemRepository mockCartItemRepository;
    @Mock
    private User mockUser;
    @Mock
    private Item mockItem;
    @Mock
    private Cart mockCart;
    @Mock
    private CartItem mockCartItem;

    @InjectMocks
    private DatabaseAccessValidator databaseAccessValidator;

    @Test
    void shouldReturnUserById() {
        when(mockRepository.accessUserRepository()).thenReturn(mockUserRepository);
        when(mockUserRepository.findById(1L)).thenReturn(Optional.of(mockUser));
        assertNotNull(databaseAccessValidator.getUserById(1L));
    }

    @Test
    void shouldThrowExceptionForMissingOptionalForUserById() {
        when(mockRepository.accessUserRepository()).thenReturn(mockUserRepository);
        when(mockUserRepository.findById(1L)).thenReturn(Optional.empty());
        assertThrows(ServiceMissingDataException.class, () -> databaseAccessValidator.getUserById(1L));
    }

    @Test
    void shouldReturnUserByLoginName() {
        when(mockRepository.accessUserRepository()).thenReturn(mockUserRepository);
        when(mockUserRepository.findByLoginName("login name")).thenReturn(Optional.of(mockUser));
        assertNotNull(databaseAccessValidator.getUserByLoginName("login name"));
    }

    @Test
    void shouldThrowExceptionForMissingOptionalForUserByLoginName() {
        when(mockRepository.accessUserRepository()).thenReturn(mockUserRepository);
        when(mockUserRepository.findByLoginName("login name")).thenReturn(Optional.empty());
        assertThrows(ServiceMissingDataException.class, () -> databaseAccessValidator.getUserByLoginName("login name"));
    }

    @Test
    void shouldReturnItemById() {
        when(mockRepository.accessItemRepository()).thenReturn(mockItemRepository);
        when(mockItemRepository.findById(1L)).thenReturn(Optional.of(mockItem));
        assertNotNull(databaseAccessValidator.getItemById(1L));
    }

    @Test
    void shouldThrowExceptionForMissingOptionalForItemById() {
        when(mockRepository.accessItemRepository()).thenReturn(mockItemRepository);
        when(mockItemRepository.findById(1L)).thenReturn(Optional.empty());
        assertThrows(ServiceMissingDataException.class, () -> databaseAccessValidator.getItemById(1L));
    }

    @Test
    void shouldReturnItemByName() {
        when(mockRepository.accessItemRepository()).thenReturn(mockItemRepository);
        when(mockItemRepository.findByName("item name")).thenReturn(Optional.of(mockItem));
        assertNotNull(databaseAccessValidator.getItemByName("item name"));
    }

    @Test
    void shouldThrowExceptionForMissingOptionalForItemByName() {
        when(mockRepository.accessItemRepository()).thenReturn(mockItemRepository);
        when(mockItemRepository.findByName("item name")).thenReturn(Optional.empty());
        assertThrows(ServiceMissingDataException.class, () -> databaseAccessValidator.getItemByName("item name"));
    }

    @Test
    void shouldReturnOpenCartByUserId() {
        when(mockRepository.accessCartRepository()).thenReturn(mockCartRepository);
        when(mockCartRepository.findOpenCartForUserId(mockUser)).thenReturn(Optional.of(mockCart));
        assertNotNull(databaseAccessValidator.getOpenCartByUserId(mockUser));
    }

    @Test
    void shouldThrowExceptionForMissingOptionalForOpenCartByUserId() {
        when(mockRepository.accessCartRepository()).thenReturn(mockCartRepository);
        when(mockCartRepository.findOpenCartForUserId(mockUser)).thenReturn(Optional.empty());
        assertThrows(ServiceMissingDataException.class, () -> databaseAccessValidator.getOpenCartByUserId(mockUser));
    }

    @Test
    void shouldReturnCartItemByCartIdAndItemId() {
        when(mockRepository.accessCartItemRepository()).thenReturn(mockCartItemRepository);
        when(mockCartItemRepository.findByCartIdAndItemId(mockCart, mockItem)).thenReturn(Optional.of(mockCartItem));
        assertNotNull(databaseAccessValidator.getCartItemByCartIdAndItemId(mockCart, mockItem));
    }

    @Test
    void shouldThrowExceptionForMissingOptionalForCartItemByCartIdAndItemId() {
        when(mockRepository.accessCartItemRepository()).thenReturn(mockCartItemRepository);
        when(mockCartItemRepository.findByCartIdAndItemId(mockCart, mockItem)).thenReturn(Optional.empty());
        assertThrows(ServiceMissingDataException.class, () -> databaseAccessValidator.getCartItemByCartIdAndItemId(mockCart, mockItem));
    }

}
