package shop.core.services.actions.customer;

import org.junit.Ignore;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import shop.core.database.CartItemRepository;
import shop.core.database.ItemRepository;
import shop.core.domain.cart.Cart;
import shop.core.domain.cart_item.CartItem;
import shop.core.domain.item.Item;
import shop.core.services.actions.shared.SecurityServiceImpl;
import shop.core.services.validators.services_validators.customer.RemoveItemFromCartValidator;
import shop.core_api.dto.cart_item.CartItemDTO;
import shop.core_api.dto.item.ItemDTO;
import shop.core_api.requests.customer.RemoveItemFromCartRequest;

import java.util.List;
import java.util.Optional;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)

@Ignore
class RemoveItemFromCartServiceImplTest {

    @Mock
    private ItemRepository mockItemRepository;
    @Mock
    private CartItemRepository mockCartItemRepository;
    @Mock
    private RemoveItemFromCartValidator mockValidator;
    @Mock
    private RemoveItemFromCartRequest mockRequest;
    @Mock
    private CartItemDTO mockCartItemDTO;
    @Mock
    private ItemDTO mockItemDTO;
    @Mock
    private SecurityServiceImpl mockSecurityService;
    @Mock
    private Cart mockCart;
    @Mock
    private Item mockItem;
    @Mock
    private Optional<Item> mockOptionalItem;
    @Mock
    private CartItem mockCartItem;

    @InjectMocks
    private RemoveItemFromCartServiceImpl service;

    @BeforeEach
    void initMock() {
        when(mockValidator.validate(any())).thenReturn(List.of());
    }

    @Test
    void shouldDeleteFromCart() {
        when(mockRequest.getCartItemDTO()).thenReturn(mockCartItemDTO);
        when(mockCartItemDTO.getItemDTO()).thenReturn(mockItemDTO);
        service.execute(mockRequest);
        verify(mockCartItemRepository).delete(any(CartItem.class));
    }

}
