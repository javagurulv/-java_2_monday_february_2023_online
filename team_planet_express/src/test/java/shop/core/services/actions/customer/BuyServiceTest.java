package shop.core.services.actions.customer;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import shop.core.database.jpa.JpaCartRepository;
import shop.core.domain.Cart;
import shop.core.enums.CartStatus;
import shop.core.requests.customer.BuyRequest;
import shop.core.services.validators.actions.customer.BuyValidator;
import shop.core.services.validators.universal.system.RepositoryAccessValidator;
import shop.core.support.CurrentUserId;

import java.util.List;

import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
class BuyServiceTest {

    @Mock
    private JpaCartRepository mockJpaCartRepository;
    @Mock
    private BuyValidator mockValidator;
    @Mock
    private RepositoryAccessValidator mockRepositoryAccessValidator;
    @Mock
    private BuyRequest mockRequest;
    @Mock
    private CurrentUserId mockCurrentUserId;
    @Mock
    private Cart mockCart;

    @InjectMocks
    private BuyService service;

    @Test
    void shouldCloseCart() {
        when(mockValidator.validate(any())).thenReturn(List.of());
        when(mockRequest.getCurrentUserId()).thenReturn(mockCurrentUserId);
        when(mockRepositoryAccessValidator.getOpenCartByUser(any())).thenReturn(mockCart);
        when(mockCart.getId()).thenReturn(1L);
        service.execute(mockRequest);
        verify(mockJpaCartRepository).updateCartStatus(1L, CartStatus.CLOSED);
    }

}
