package shop.core.services.actions.customer;

import org.junit.Ignore;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.data.jpa.domain.Specification;
import shop.core.database.CartItemRepository;
import shop.core.database.CartRepository;
import shop.core.domain.cart.Cart;
import shop.core.domain.cart_item.CartItem;
import shop.core.domain.item.Item;
import shop.core.domain.user.User;
import shop.core.services.actions.shared.SecurityServiceImpl;
import shop.core.services.cart.CartService;
import shop.core.services.validators.services_validators.customer.ListCartItemValidator;
import shop.core_api.requests.customer.GetListCartItemsRequest;
import shop.core_api.responses.customer.GetListCartItemsResponse;

import java.math.BigDecimal;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)

@Ignore
class GetListCartItemsServiceImplTest {

    @Mock
    private CartItemRepository mockCartItemRepository;
    @Mock
    private CartRepository mockCartRepository;
    @Mock
    private ListCartItemValidator mockListCartItemValidator;
    @Mock
    private CartService mockCartService;
    @Mock
    private GetListCartItemsRequest mockRequest;
    @Mock
    private SecurityServiceImpl mockSecurityService;
    @Mock
    private Cart mockCart;
    @Mock
    private Optional<Cart> mockOptionalCart;
    @Mock
    private CartItem mockCartItem;
    @Mock
    private Item mockItem;

    @InjectMocks
    private GetListCartItemsServiceImpl service;

    @BeforeEach
    void initMock() {
        when(mockSecurityService.getAuthenticatedUserFromDB()).thenReturn(Optional.of(new User()));
        when(mockCartRepository.findOne(any(Specification.class))).thenReturn(mockOptionalCart);
        when(mockOptionalCart.get()).thenReturn(mockCart);
    }

    @Test
    void shouldReturnListCartItem() {
        when(mockCartItem.getCart()).thenReturn(mockCart);
        when(mockCartItem.getItem()).thenReturn(mockItem);
        when(mockItem.getId()).thenReturn(1L);
        when(mockCart.getId()).thenReturn(1L);
        when(mockItem.getPrice()).thenReturn(BigDecimal.valueOf(1));
        when(mockListCartItemValidator.validate(any())).thenReturn(List.of());
        when(mockCartItemRepository.findAll(any(Specification.class))).thenReturn(List.of(mockCartItem, mockCartItem));
        GetListCartItemsResponse response = service.execute(mockRequest);
        assertEquals(response.getCartItemsDTO().size(), 2);
    }

    @Test
    void shouldReturnSum() {
        when(mockListCartItemValidator.validate(any())).thenReturn(List.of());
        when(mockCartService.getSum(0L)).thenReturn(BigDecimal.valueOf(1));
        GetListCartItemsResponse response = service.execute(mockRequest);
        assertEquals(response.getCartTotal(), BigDecimal.valueOf(1));
    }
}
