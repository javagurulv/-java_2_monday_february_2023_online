package shop.core.services.user;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import shop.core.database.CartRepository;
import shop.core.database.Repository;
import shop.core.database.UserRepository;
import shop.core.domain.cart.Cart;
import shop.core.domain.cart.CartStatus;
import shop.core.domain.user.User;
import shop.core.domain.user.UserRole;
import shop.matchers.CartMatcher;
import shop.matchers.UserMatcher;

import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
class UserServiceTest {

    @Mock
    private Repository mockRepository;
    @Mock
    private UserRepository mockUserRepository;
    @Mock
    private CartRepository mockCartRepository;
    @Mock
    private User mockUser;
    @Mock
    private Cart mockCart;

    @InjectMocks
    private UserService service;

    @Test
    void shouldSaveUserAndCart() {
        when(mockRepository.accessUserRepository()).thenReturn(mockUserRepository);
        when(mockRepository.accessCartRepository()).thenReturn(mockCartRepository);
        when(mockUserRepository.save(any(User.class))).thenReturn(mockUser);
        UserCreationData userCreationData =
                new UserCreationData("Name", "login name", "password", UserRole.GUEST);
        service.createUser(userCreationData);
        verify(mockUserRepository)
                .save(argThat(new UserMatcher("Name", "login name", "password", UserRole.GUEST)));
        verify(mockCartRepository)
                .save(argThat(new CartMatcher(mockUser, CartStatus.OPEN)));
    }

    @Test
    void shouldReturnExistingGuest() {
        when(mockRepository.accessUserRepository()).thenReturn(mockUserRepository);
        when(mockRepository.accessCartRepository()).thenReturn(mockCartRepository);
        when(mockUserRepository.getAllUsers()).thenReturn(List.of(mockUser));
        when(mockUser.getUserRole()).thenReturn(UserRole.GUEST);
        when(mockUser.getId()).thenReturn(1L);
        when(mockCartRepository.findOpenCartForUserId(1L)).thenReturn(Optional.of(mockCart));
        assertTrue(service.findGuestWithOpenCart().isPresent());
    }

    @Test
    void shouldReturnEmptyOptionalForNoGuest() {
        when(mockRepository.accessUserRepository()).thenReturn(mockUserRepository);
        when(mockUserRepository.getAllUsers()).thenReturn(List.of(mockUser));
        when(mockUser.getUserRole()).thenReturn(UserRole.ADMIN);
        assertTrue(service.findGuestWithOpenCart().isEmpty());
    }

    @Test
    void shouldReturnEmptyOptionalForNoOpenCart() {
        when(mockRepository.accessUserRepository()).thenReturn(mockUserRepository);
        when(mockRepository.accessCartRepository()).thenReturn(mockCartRepository);
        when(mockUserRepository.getAllUsers()).thenReturn(List.of(mockUser));
        when(mockUser.getUserRole()).thenReturn(UserRole.GUEST);
        when(mockUser.getId()).thenReturn(1L);
        when(mockCartRepository.findOpenCartForUserId(1L)).thenReturn(Optional.empty());
        assertTrue(service.findGuestWithOpenCart().isEmpty());
    }

}
