package shop.core.services.actions.customer;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import shop.core.database.CartRepository;
import shop.core.database.Repository;
import shop.core.domain.cart.Cart;
import shop.core.domain.cart.CartStatus;
import shop.core.requests.customer.BuyRequest;
import shop.core.services.validators.actions.customer.BuyValidator;
import shop.core.services.validators.universal.system.DatabaseAccessValidator;
import shop.core.support.CurrentUser;

import java.util.List;

import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
class BuyServiceTest {

    @Mock
    private Repository mockRepository;
    @Mock
    private BuyValidator mockValidator;
    @Mock
    private DatabaseAccessValidator mockDatabaseAccessValidator;
    @Mock
    private BuyRequest mockRequest;
    @Mock
    private CartRepository mockCartRepository;
    @Mock
    private CurrentUser mockCurrentUser;
    @Mock
    private Cart mockCart;

    @InjectMocks
    private BuyService service;

    @Test
    void shouldCloseCart() {
        when(mockRepository.accessCartDatabase()).thenReturn(mockCartRepository);
        when(mockValidator.validate(any())).thenReturn(List.of());
        when(mockRequest.getUserId()).thenReturn(mockCurrentUser);
        when(mockDatabaseAccessValidator.getOpenCartByUserId(any())).thenReturn(mockCart);
        when(mockCart.getId()).thenReturn(1L);
        service.execute(mockRequest);
        verify(mockCartRepository).changeCartStatus(1L, CartStatus.CLOSED);
    }

}
