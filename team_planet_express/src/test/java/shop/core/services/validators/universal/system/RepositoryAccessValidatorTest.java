package shop.core.services.validators.universal.system;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import shop.core.database.CartItemRepository;
import shop.core.database.CartRepository;
import shop.core.database.ItemRepository;
import shop.core.database.UserRepository;
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
    private DatabaseAccessProvider databaseAccessProvider;

    @Test
    void shouldReturnUserById() {
        when(mockUserRepository.findById(1L)).thenReturn(Optional.of(mockUser));
        assertNotNull(databaseAccessProvider.getUserById(1L));
    }

    @Test
    void shouldThrowExceptionForMissingOptionalForUserById() {
        when(mockUserRepository.findById(1L)).thenReturn(Optional.empty());
        assertThrows(ServiceMissingDataException.class, () -> databaseAccessProvider.getUserById(1L));
    }

    @Test
    void shouldReturnUserByLoginName() {
        when(mockUserRepository.findByLoginName("login name")).thenReturn(Optional.of(mockUser));
        assertNotNull(databaseAccessProvider.getUserByLoginName("login name"));
    }

    @Test
    void shouldThrowExceptionForMissingOptionalForUserByLoginName() {
        when(mockUserRepository.findByLoginName("login name")).thenReturn(Optional.empty());
        assertThrows(ServiceMissingDataException.class, () -> databaseAccessProvider.getUserByLoginName("login name"));
    }

    @Test
    void shouldReturnItemById() {
        when(mockItemRepository.findById(1L)).thenReturn(Optional.of(mockItem));
        assertNotNull(databaseAccessProvider.getItemById(1L));
    }

    @Test
    void shouldThrowExceptionForMissingOptionalForItemById() {
        when(mockItemRepository.findById(1L)).thenReturn(Optional.empty());
        assertThrows(ServiceMissingDataException.class, () -> databaseAccessProvider.getItemById(1L));
    }

    @Test
    void shouldReturnItemByName() {
        when(mockItemRepository.findByName("item name")).thenReturn(Optional.of(mockItem));
        assertNotNull(databaseAccessProvider.getItemByName("item name"));
    }

    @Test
    void shouldThrowExceptionForMissingOptionalForItemByName() {
        when(mockItemRepository.findByName("item name")).thenReturn(Optional.empty());
        assertThrows(ServiceMissingDataException.class, () -> databaseAccessProvider.getItemByName("item name"));
    }

    @Test
    void shouldReturnOpenCartByUserId() {
        when(mockCartRepository.findOpenCartForUserId(1L)).thenReturn(Optional.of(mockCart));
        assertNotNull(databaseAccessProvider.getOpenCartByUserId(1L));
    }

    @Test
    void shouldThrowExceptionForMissingOptionalForOpenCartByUserId() {
        when(mockCartRepository.findOpenCartForUserId(1L)).thenReturn(Optional.empty());
        assertThrows(ServiceMissingDataException.class, () -> databaseAccessProvider.getOpenCartByUserId(1L));
    }

    @Test
    void shouldReturnCartItemByCartIdAndItemId() {
        when(mockCartItemRepository.findByCartIdAndItemId(1L, 1L)).thenReturn(Optional.of(mockCartItem));
        assertNotNull(databaseAccessProvider.getCartItemByCartIdAndItemId(1L, 1L));
    }

    @Test
    void shouldThrowExceptionForMissingOptionalForCartItemByCartIdAndItemId() {
        when(mockCartItemRepository.findByCartIdAndItemId(1L, 1L)).thenReturn(Optional.empty());
        assertThrows(ServiceMissingDataException.class, () -> databaseAccessProvider.getCartItemByCartIdAndItemId(1L, 1L));
    }

}
