package shop.core.services.cart;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import shop.core.database.jpa.JpaCartItemRepository;
import shop.core.domain.CartItem;
import shop.core.domain.Item;
import shop.core.services.validators.universal.system.RepositoryAccessValidator;

import java.math.BigDecimal;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.ArgumentMatchers.anyLong;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
class CartServiceTest {

    @Mock
    private JpaCartItemRepository mockJpaCartItemRepository;
    @Mock
    private RepositoryAccessValidator mockRepositoryAccessValidator;
    @Mock
    private CartItem mockCartItem;
    @Mock
    private Item mockItem;

    @InjectMocks
    private CartService service;

    @Test
    void shouldReturnSum() {
        when(mockJpaCartItemRepository.findByCartId(1L)).thenReturn(List.of(mockCartItem, mockCartItem, mockCartItem));
        when(mockCartItem.getItem()).thenReturn(mockItem);
        when(mockRepositoryAccessValidator.getItemById(anyLong())).thenReturn(mockItem);
        when(mockItem.getPrice()).thenReturn(new BigDecimal("10"), new BigDecimal("7.52"), new BigDecimal("0.27"));
        when(mockCartItem.getOrderedQuantity()).thenReturn(1, 3, 7);
        BigDecimal actualResult = service.getSum(1L);
        assertEquals(new BigDecimal("34.45"), actualResult);
    }

}
