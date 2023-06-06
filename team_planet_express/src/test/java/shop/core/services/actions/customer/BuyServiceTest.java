package shop.core.services.actions.customer;

import org.junit.Ignore;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import shop.core.database.CartRepository;
import shop.core.domain.cart.Cart;
import shop.core.domain.cart.CartStatus;
import shop.core.domain.user.User;
import shop.core.requests.customer.BuyRequest;
import shop.core.services.actions.shared.SecurityService;
import shop.core.services.validators.actions.customer.BuyValidator;
import shop.core.services.validators.universal.system.DatabaseAccessValidator;

import java.util.List;
import java.util.Optional;

import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)

@Ignore
class BuyServiceTest {

    @Mock
    private CartRepository mockCartRepository;
    @Mock
    private BuyValidator mockValidator;
    @Mock
    private DatabaseAccessValidator mockDatabaseAccessValidator;
    @Mock
    private BuyRequest mockRequest;
    @Mock
    private SecurityService mockSecurityService;
    @Mock
    private Cart mockCart;

    @InjectMocks
    private BuyService service;

    @Test
    void shouldCloseCart() {
        when(mockValidator.validate(any())).thenReturn(List.of());
        when(mockSecurityService.getAuthenticatedUserFromDB()).thenReturn(Optional.of(new User()));
        when(mockDatabaseAccessValidator.getOpenCartByUserId(any())).thenReturn(mockCart);
        when(mockCart.getId()).thenReturn(1L);
        service.execute(mockRequest);
        verify(mockCartRepository).changeCartStatus(1L, CartStatus.CLOSED);
    }

}
