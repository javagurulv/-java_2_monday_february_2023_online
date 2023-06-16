package shop.core.services.user;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import shop.core.database.jpa.JpaCartRepository;
import shop.core.database.jpa.JpaUserRepository;
import shop.core.domain.Cart;
import shop.core.domain.User;
import shop.core.enums.CartStatus;
import shop.core.enums.UserRole;
import shop.matchers.CartMatcher;
import shop.matchers.UserMatcher;

import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
class UserServiceTest {

    @Mock
    private JpaUserRepository mockJpaUserRepository;
    @Mock
    private JpaCartRepository mockJpaCartRepository;
    @Mock
    private User mockUser;
    @Mock
    private Cart mockCart;

    @InjectMocks
    private UserService service;

    @Test
    void shouldSaveUserAndCart() {
        when(mockJpaUserRepository.save(any(User.class))).thenReturn(mockUser);
        UserCreationData userCreationData =
                new UserCreationData("Name", "login name", "password", UserRole.GUEST);
        service.createUser(userCreationData);
        verify(mockJpaUserRepository)
                .save(argThat(new UserMatcher("Name", "login name", "password", UserRole.GUEST)));
        verify(mockJpaCartRepository)
                .save(argThat(new CartMatcher(mockUser, CartStatus.OPEN)));
    }

    @Test
    void shouldReturnExistingGuest() {
        when(mockJpaUserRepository.findAll()).thenReturn(List.of(mockUser));
        when(mockUser.getUserRole()).thenReturn(UserRole.GUEST);
        when(mockUser.getId()).thenReturn(1L);
        when(mockJpaCartRepository.findOpenCartByUserId(1L)).thenReturn(Optional.of(mockCart));
        assertTrue(service.findGuestWithOpenCart().isPresent());
    }

    @Test
    void shouldReturnEmptyOptionalForNoGuest() {
        when(mockJpaUserRepository.findAll()).thenReturn(List.of(mockUser));
        when(mockUser.getUserRole()).thenReturn(UserRole.ADMIN);
        assertTrue(service.findGuestWithOpenCart().isEmpty());
    }

    @Test
    void shouldReturnEmptyOptionalForNoOpenCart() {
        when(mockJpaUserRepository.findAll()).thenReturn(List.of(mockUser));
        when(mockUser.getUserRole()).thenReturn(UserRole.GUEST);
        when(mockUser.getId()).thenReturn(1L);
        when(mockJpaCartRepository.findOpenCartByUserId(1L)).thenReturn(Optional.empty());
        assertTrue(service.findGuestWithOpenCart().isEmpty());
    }

}
