package shop.core.services.actions.customer;

import org.junit.Ignore;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.data.jpa.domain.Specification;
import shop.core.database.CartItemRepository;
import shop.core.database.CartRepository;
import shop.core.domain.cart.Cart;
import shop.core.domain.cart.CartStatus;
import shop.core.domain.cart_item.CartItem;
import shop.core.domain.item.Item;
import shop.core.domain.user.User;
import shop.core.services.actions.shared.SecurityServiceImpl;
import shop.core.services.validators.services_validators.customer.BuyValidator;
import shop.core_api.requests.customer.BuyRequest;

import java.util.List;
import java.util.Optional;

import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)

@Ignore
class BuyServiceImpTest {

    @Mock
    private CartRepository mockCartRepository;
    @Mock
    private CartItemRepository mockCartItemRepository;
    @Mock
    private BuyValidator mockValidator;
    @Mock
    private BuyRequest mockRequest;
    @Mock
    private SecurityServiceImpl mockSecurityService;
    @Mock
    private Cart mockCart;
    @Mock
    private CartItem mockCartItem;
    @Mock
    private Item mockItem;
    @Mock
    private Optional<Cart> mockOptionalCart;

    @InjectMocks
    private BuyServiceImpl service;

    @Test
    void shouldCloseCart() {
        when(mockValidator.validate(any())).thenReturn(List.of());
        when(mockSecurityService.getAuthenticatedUserFromDB()).thenReturn(Optional.of(new User()));
        when(mockCartRepository.findOne(any(Specification.class))).thenReturn(mockOptionalCart);
        when(mockCartItemRepository.findAll(any(Specification.class))).thenReturn(List.of(mockCartItem));
        when(mockOptionalCart.get()).thenReturn(mockCart);
        when(mockCartItem.getItem()).thenReturn(mockItem);
        when(mockItem.getAvailableQuantity()).thenReturn(15);
        service.execute(mockRequest);
        verify(mockCart).setStatus(CartStatus.CLOSED);
    }

}
