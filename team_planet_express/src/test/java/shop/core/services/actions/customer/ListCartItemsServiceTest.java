package shop.core.services.actions.customer;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import shop.core.converters.CartItemConverter;
import shop.core.database.jpa.JpaCartItemRepository;
import shop.core.domain.Cart;
import shop.core.domain.CartItem;
import shop.core.dtos.CartItemDto;
import shop.core.requests.customer.ListCartItemsRequest;
import shop.core.responses.customer.ListCartItemsResponse;
import shop.core.services.validators.actions.customer.ListCartItemValidator;
import shop.core.services.validators.universal.system.RepositoryAccessValidator;
import shop.core.support.CurrentUserId;

import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
class ListCartItemsServiceTest {

    @Mock
    private JpaCartItemRepository mockJpaCartItemRepository;
    @Mock
    private ListCartItemValidator mockValidator;
    @Mock
    private RepositoryAccessValidator mockRepositoryAccessValidator;
    @Mock
    private CartItemConverter mockCartItemConverter;
    @Mock
    private ListCartItemsRequest mockRequest;
    @Mock
    private CurrentUserId mockCurrentUserId;
    @Mock
    private Cart mockCart;
    @Mock
    private CartItem mockCartItem;
    @Mock
    private CartItemDto mockCartItemDto;

    @InjectMocks
    private ListCartItemsService service;

    @BeforeEach
    void initMock() {
        when(mockRequest.getCurrentUserId()).thenReturn(mockCurrentUserId);
    }

    @Test
    void shouldReturnListCartItem() {
        when(mockRepositoryAccessValidator.getOpenCartByUser(any())).thenReturn(mockCart);
        when(mockJpaCartItemRepository.findByCart(any(Cart.class))).thenReturn(List.of(mockCartItem, mockCartItem));
        when(mockCartItemConverter.toCartItemDto(List.of(mockCartItem, mockCartItem))).thenReturn(List.of(mockCartItemDto, mockCartItemDto));
        ListCartItemsResponse response = service.execute(mockRequest);
        assertEquals(response.getCartItems().size(), 2);
    }

}
