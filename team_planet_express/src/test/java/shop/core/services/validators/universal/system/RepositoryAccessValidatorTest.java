package shop.core.services.validators.universal.system;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import shop.core.database.jpa.JpaCartItemRepository;
import shop.core.database.jpa.JpaCartRepository;
import shop.core.database.jpa.JpaItemRepository;
import shop.core.database.jpa.JpaUserRepository;
import shop.core.domain.Cart;
import shop.core.domain.CartItem;
import shop.core.domain.Item;
import shop.core.domain.User;
import shop.core.services.exception.ServiceMissingDataException;

import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
class RepositoryAccessValidatorTest {

    @Mock
    private JpaUserRepository mockJpaUserRepository;
    @Mock
    private JpaItemRepository mockJpaItemRepository;
    @Mock
    private JpaCartRepository mockJpaCartRepository;
    @Mock
    private JpaCartItemRepository mockJpaCartItemRepository;
    @Mock
    private User mockUser;
    @Mock
    private Item mockItem;
    @Mock
    private Cart mockCart;
    @Mock
    private CartItem mockCartItem;

    @InjectMocks
    private RepositoryAccessValidator repositoryAccessValidator;

    @Test
    void shouldReturnUserById() {
        when(mockJpaUserRepository.findById(1L)).thenReturn(Optional.of(mockUser));
        assertNotNull(repositoryAccessValidator.getUserById(1L));
    }

    @Test
    void shouldThrowExceptionForMissingOptionalForUserById() {
        when(mockJpaUserRepository.findById(1L)).thenReturn(Optional.empty());
        assertThrows(ServiceMissingDataException.class, () -> repositoryAccessValidator.getUserById(1L));
    }

    @Test
    void shouldReturnUserByLoginName() {
        when(mockJpaUserRepository.findFirstByLogin("login name")).thenReturn(Optional.of(mockUser));
        assertNotNull(repositoryAccessValidator.getUserByLoginName("login name"));
    }

    @Test
    void shouldThrowExceptionForMissingOptionalForUserByLoginName() {
        when(mockJpaUserRepository.findFirstByLogin("login name")).thenReturn(Optional.empty());
        assertThrows(ServiceMissingDataException.class, () -> repositoryAccessValidator.getUserByLoginName("login name"));
    }

    @Test
    void shouldReturnItemById() {
        when(mockJpaItemRepository.findById(1L)).thenReturn(Optional.of(mockItem));
        assertNotNull(repositoryAccessValidator.getItemById(1L));
    }

    @Test
    void shouldThrowExceptionForMissingOptionalForItemById() {
        when(mockJpaItemRepository.findById(1L)).thenReturn(Optional.empty());
        assertThrows(ServiceMissingDataException.class, () -> repositoryAccessValidator.getItemById(1L));
    }

    @Test
    void shouldReturnItemByName() {
        when(mockJpaItemRepository.findFirstByName("item name")).thenReturn(Optional.of(mockItem));
        assertNotNull(repositoryAccessValidator.getItemByName("item name"));
    }

    @Test
    void shouldThrowExceptionForMissingOptionalForItemByName() {
        when(mockJpaItemRepository.findFirstByName("item name")).thenReturn(Optional.empty());
        assertThrows(ServiceMissingDataException.class, () -> repositoryAccessValidator.getItemByName("item name"));
    }

    @Test
    void shouldReturnOpenCartByUser() {
        when(mockJpaCartRepository.findOpenCartByUser(mockUser)).thenReturn(Optional.of(mockCart));
        assertNotNull(repositoryAccessValidator.getOpenCartByUser(mockUser));
    }

    @Test
    void shouldThrowExceptionForMissingOptionalForOpenCartByUser() {
        when(mockJpaCartRepository.findOpenCartByUser(mockUser)).thenReturn(Optional.empty());
        assertThrows(ServiceMissingDataException.class, () -> repositoryAccessValidator.getOpenCartByUser(mockUser));
    }

    @Test
    void shouldReturnCartItemByCartIdAndItem() {
        when(mockJpaCartItemRepository.findFirstByCartAndItem(mockCart, mockItem)).thenReturn(Optional.of(mockCartItem));
        assertNotNull(repositoryAccessValidator.getCartItemByCartAndItem(mockCart, mockItem));
    }

    @Test
    void shouldThrowExceptionForMissingOptionalForCartItemByCartAndItem() {
        when(mockJpaCartItemRepository.findFirstByCartAndItem(mockCart, mockItem)).thenReturn(Optional.empty());
        assertThrows(ServiceMissingDataException.class, () -> repositoryAccessValidator.getCartItemByCartAndItem(mockCart, mockItem));
    }

}
